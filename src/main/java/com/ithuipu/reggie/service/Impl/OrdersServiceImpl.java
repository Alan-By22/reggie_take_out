package com.ithuipu.reggie.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ithuipu.reggie.entity.Orders;
import com.ithuipu.reggie.mapper.OrdersMapper;
import com.ithuipu.reggie.service.OrdersService;
import org.springframework.stereotype.Service;


/**
 * @className: OrdersServiceImpl
 * @author: Mr.BingYu
 * @description: 
 * @date: 2022/12/13 11:10
 **/

    

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService{

}
