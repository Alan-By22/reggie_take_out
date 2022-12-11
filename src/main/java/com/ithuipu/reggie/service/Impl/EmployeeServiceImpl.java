package com.ithuipu.reggie.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ithuipu.reggie.entity.Employee;
import com.ithuipu.reggie.mapper.EmployeeMapper;
import com.ithuipu.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @className: EmployeeServiceImpl
 * @author: Mr.BingYu
 * @description:
 * @date: 2022/12/11 18:16
 **/

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
