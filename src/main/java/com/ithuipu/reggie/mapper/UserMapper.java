package com.ithuipu.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ithuipu.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * @className: UserMapper
 * @author: Mr.BingYu
 * @description: 
 * @date: 2022/12/13 11:10
 **/


@Mapper
public interface UserMapper extends BaseMapper<User> {
}