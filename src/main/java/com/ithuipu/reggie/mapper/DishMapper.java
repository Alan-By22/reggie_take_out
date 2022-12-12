package com.ithuipu.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ithuipu.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;


/**
 * @className: DishMapper
 * @author: Mr.BingYu
 * @description: 
 * @date: 2022/12/12 23:00
 **/


@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}