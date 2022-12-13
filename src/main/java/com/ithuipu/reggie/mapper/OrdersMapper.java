package com.ithuipu.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ithuipu.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;


/**
 * @className: OrdersMapper
 * @author: Mr.BingYu
 * @description: 
 * @date: 2022/12/13 11:10
 **/


@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}