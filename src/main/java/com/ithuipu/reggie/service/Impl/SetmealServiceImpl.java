package com.ithuipu.reggie.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ithuipu.reggie.common.CustomException;
import com.ithuipu.reggie.entity.Setmeal;
import com.ithuipu.reggie.entity.SetmealDish;
import com.ithuipu.reggie.entity.SetmealDto;
import com.ithuipu.reggie.mapper.SetmealMapper;
import com.ithuipu.reggie.service.SetmealDtoService;
import com.ithuipu.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @className: SetmealServiceImpl
 * @author: Mr.BingYu
 * @description:
 * @date: 2022/12/13 11:10
 **/


@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    /**
     * 注入service
     */
    @Autowired
    private SetmealDtoService setmealDtoService;

    /**
     * A. 保存套餐基本信息
     * B. 获取套餐关联的菜品集合，并为集合中的每一个元素赋值套餐ID(setmealId)
     * C. 批量保存套餐关联的菜品集合
     * <p>
     * 新增套餐,同事需要保存套餐和菜品的关联关系
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveWithDish(SetmealDto setmealDto) {
        //保存套餐的基本信息,操作setmeal,执行insert
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //保存套餐和菜品关联信息,操作setmeal_dish,执行insert
        setmealDtoService.saveBatch(setmealDishes);

    }
    /**删除套餐,同时需要删除套餐和菜品的关联数据*/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeWithDish(List<Long> ids) {
        //查询套餐状态,确实是否可以删除
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(Setmeal::getId,ids);
        lambdaQueryWrapper.eq(Setmeal::getStatus,1);

        int count = this.count(lambdaQueryWrapper);
        if (count > 0) {
            //如果不能删除,抛出异常
            throw new CustomException("套餐正在售卖,无法删除");
        }

        //如果可以删除,先删除套餐表中的数据---selmeal
        this.removeByIds(ids);

        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper1  =new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.in(SetmealDish::getSetmealId,ids);

        //删除表中关联的数据---setmeal_dish
        setmealDtoService.remove(lambdaQueryWrapper1);


    }
}
