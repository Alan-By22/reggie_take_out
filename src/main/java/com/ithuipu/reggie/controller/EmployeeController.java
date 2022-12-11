package com.ithuipu.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ithuipu.reggie.common.R;
import com.ithuipu.reggie.entity.Employee;
import com.ithuipu.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @className: EmployeeController
 * @author: Mr.BingYu
 * @description:
 * @date: 2022/12/11 19:01
 **/

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    /**
     * 注入service
     */
    @Autowired
    private EmployeeService employeeService;


    /**
     * 后台用户的登陆
     */
    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpServletRequest request) {
        //1、将页面提交的密码password进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2、根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(lambdaQueryWrapper);

        //3、如果没有查询到则返回登录失败结果
        if (emp == null) {
            return R.error("用户名或密码错误!!!");
        }

        //4、密码比对，如果不一致则返回登录失败结果
        if (!emp.getPassword().equals(password)) {
            return R.error("用户名或密码错误!!!");
        }

        //5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if (emp.getStatus() == 0) {
            return R.error("账号已禁用");
        }

        //6、登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    /**
     * 员工的退出
     */
    @PostMapping("/logout")
    public R<String> loginOut(HttpServletRequest request) {
        //清理Session中保存的员工id
        request.getSession().removeAttribute("employee");
        return R.success("成功退出当前用户");
    }
}
