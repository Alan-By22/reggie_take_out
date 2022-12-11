package com.ithuipu.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ithuipu.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: EmployeeMapper
 * @author: Mr.BingYu
 * @description:
 * @date: 2022/12/11 18:14
 **/

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
