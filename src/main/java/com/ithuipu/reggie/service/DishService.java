package com.ithuipu.reggie.service;

import com.ithuipu.reggie.entity.DishDto;
import com.ithuipu.reggie.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * @className: DishService
 * @author: Mr.BingYu
 * @description:
 * @date: 2022/12/12 23:00
 **/


public interface DishService extends IService<Dish> {

    /**
     * 新增菜品，同时插入菜品对应的口味数据，需要操作两张表：dish、dish_flavor
     * 封装Dish的flavors
     */
    void saveWithFlavor(DishDto dishDto);
}
