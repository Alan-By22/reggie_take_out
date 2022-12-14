package com.ithuipu.reggie.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ithuipu.reggie.entity.Dish;
import com.ithuipu.reggie.entity.DishDto;
import com.ithuipu.reggie.entity.DishFlavor;
import com.ithuipu.reggie.mapper.DishMapper;
import com.ithuipu.reggie.service.DishFlavorService;
import com.ithuipu.reggie.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @className: DishServiceImpl
 * @author: Mr.BingYu
 * @description:
 * @date: 2022/12/12 23:00
 **/


@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {


    /**
     * 注入Service
     */
    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 封装Dish的flavors
     * 新增菜品，同时保存对应的口味数据
     * <p>
     * 由于在 saveWithFlavor 方法中，进行了两次数据库的保存操作，
     * 操作了两张表，那么为了保证数据的一致性，我们需要在方法上加上注解 @Transactional来控制事务。
     *
     * @Transactional要想生效,在引导类上加注解 @EnableTransactionManagement**
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveWithFlavor(DishDto dishDto) {
        //保存菜品的基本信息到dish
        this.save(dishDto);
        //获取保存的菜品ID
        Long dishId = dishDto.getId();

        //获取菜品口味列表，遍历列表，为菜品口味对象属性dishId赋值;
        List<DishFlavor> flavors = dishDto.getFlavors();
        //批量保存菜品口味列表
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        //保存菜品口味数据到菜品口味表dish_flavor
        dishFlavorService.saveBatch(flavors);
    }

    @Override
    public DishDto getByIdWithFlavor(Long id) {

        //查询菜品基本信息,从dish查询
        Dish dish = this.getById(id);

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);

        //查询当前菜品对应的口味,从dish_flavor中
        LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DishFlavor::getDishId, dish.getId());
        List<DishFlavor> list = dishFlavorService.list(lambdaQueryWrapper);
        dishDto.setFlavors(list);
        return dishDto;
    }

    /**
     * 需要更新dish菜品基本信息表，还需要更新dish_flavor菜品口味表
     * 先删除，后添加
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWithFlavor(DishDto dishDto) {
        //更新dish表基本信息
        this.updateById(dishDto);

        //清理当前菜品的口味---dish_flavor表的delete
        LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DishFlavor::getDishId, dishDto.getId());

        dishFlavorService.remove(lambdaQueryWrapper);

        //添加当前提交修改的口味数据----dish_flavor表的insert
        List<DishFlavor> flavors = dishDto.getFlavors();

        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);
    }
}























