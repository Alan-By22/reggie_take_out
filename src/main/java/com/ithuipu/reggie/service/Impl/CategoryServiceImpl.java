package com.ithuipu.reggie.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ithuipu.reggie.common.CustomException;
import com.ithuipu.reggie.entity.Category;
import com.ithuipu.reggie.entity.Dish;
import com.ithuipu.reggie.entity.Setmeal;
import com.ithuipu.reggie.mapper.CategoryMapper;
import com.ithuipu.reggie.service.CategoryService;
import com.ithuipu.reggie.service.DishService;
import com.ithuipu.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @className: CategoryServiceImpl
 * @author: Mr.BingYu
 * @description:
 * @date: 2022/12/12 23:54
 **/


@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    /**
     * 注入service
     */
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    /**
     * 根据id删除分类，删除之前需要进行判断
     */
    @Override
    public void remove(Long id) {
        //添加查询条件,根据分类id进行查询菜品数据
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 =dishService.count(dishLambdaQueryWrapper);
        //如果已经关联,抛出一个业务异常
        if (count1 > 0) {
            throw new CustomException("当前分类下关联了菜品,不能删除");
        }

        //查询当前分类是否关联了套餐,如果已经关联,抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);
        if (count2 > 0) {
            throw new CustomException("当前分类下关联了套餐,不能删除");
        }

        //正常删除
        super.removeById(id);
    }
}
