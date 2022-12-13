package com.ithuipu.reggie.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ithuipu.reggie.entity.User;
import com.ithuipu.reggie.mapper.UserMapper;
import com.ithuipu.reggie.service.UserService;
import org.springframework.stereotype.Service;


/**
 * @className: UserServiceImpl
 * @author: Mr.BingYu
 * @description: 
 * @date: 2022/12/13 11:10
 **/

    

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}
