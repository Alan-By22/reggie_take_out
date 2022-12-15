package com.ithuipu.reggie.service;

import com.ithuipu.reggie.entity.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ithuipu.reggie.entity.SetmealDto;


/**
 * @className: SetmealService
 * @author: Mr.BingYu
 * @description:
 * @date: 2022/12/13 11:10
 **/


public interface SetmealService extends IService<Setmeal> {

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     */
    void saveWithDish(SetmealDto setmealDto);

}
