package com.ithuipu.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ithuipu.reggie.common.R;
import com.ithuipu.reggie.entity.Employee;
import com.ithuipu.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

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


    /**
     * 测试查看路径,发送json数据,post
     * name: "12", phone: "18392969156", sex: "1", idNumber: "610159999999999999", username: "159"
     * <p>
     * 新增员工
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增员工,员工信息{}", employee.toString());

        //1.设置初始密码123456,需要MD5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        ////2.创建时间,修改时间信息
        //employee.setCreateTime(LocalDateTime.now());
        //employee.setUpdateTime(LocalDateTime.now());
        //
        ////获取当前登陆的id
        //Long empId = (Long) request.getSession().getAttribute("employee");
        ////创建人,修改人信息
        //employee.setCreateUser(empId);
        //employee.setUpdateUser(empId);

        employeeService.save(employee);
        return R.success("添加成功");
    }

    /**
     * http://localhost:8080/employee/page?page=1&pageSize=10&name=admin
     * 请求方式GET请求  路径/employee/page请求参数page , pageSize , name
     * <p>
     * 员工信息分页查询
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        log.info("page={},pageSize={},name={}", page, pageSize, name);
        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        //添加排序条件
        lambdaQueryWrapper.orderByDesc(Employee::getUpdateTime);

        //执行查询
        employeeService.page(pageInfo, lambdaQueryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 请求方式PUT请求   路径/employee请求参数{"id":xxx,"status":xxx}---member-list.html-statusHandle
     * <p>
     * 根据id修改员工展示信息
     */
    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        log.info(employee.toString());

        //获得线程id
        long id = Thread.currentThread().getId();
        log.info("ControllerId为：{}",id);

        //Long empId = (Long) request.getSession().getAttribute("employee");
        //employee.setUpdateTime(LocalDateTime.now());
        //employee.setUpdateUser(empId);

        employeeService.updateById(employee);
        return R.success("员工信息修改成功");
    }


    /**
     * 请求方式GET  请求路径/employee/{id}
     * <p>
     * 根据id查询员工信息
     */
    @GetMapping("/{id}")
    public R<Employee> selectById(@PathVariable Long id) {
        log.info("根据id查询员工的信息----");

        Employee employee = employeeService.getById(id);
        if (employee != null) {
            return R.success(employee);
        }
        return R.error("查询失败,请检查后重试...");
    }


}
