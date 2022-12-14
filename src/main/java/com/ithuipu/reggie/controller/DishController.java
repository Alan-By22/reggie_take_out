package com.ithuipu.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ithuipu.reggie.DishDto;
import com.ithuipu.reggie.common.R;
import com.ithuipu.reggie.entity.Dish;
import com.ithuipu.reggie.service.DishFlavorService;
import com.ithuipu.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @className: DishController
 * @author: Mr.BingYu
 * @description:
 * @date: 2022/12/12 23:03
 **/

@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

    /**
     * 注入service
     */
    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * http://localhost:8080/dish/page?page=1&pageSize=10&name=111
     * 请求方法: GET
     * page/food/list.html
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        log.info("page = {},pageSize = {},name = {}", page, pageSize, name);
        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Dish::getName, name);
        //添加排序条件
        queryWrapper.orderByDesc(Dish::getUpdateTime);

        //执行查询
        dishService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 请求网址: http://localhost:8080/dish
     * 请求方法: POST
     * <p>
     * 新增菜品
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto) {
        log.info(dishDto.toString());

        dishService.saveWithFlavor(dishDto);
        return R.success("添加菜品成功");
    }
}
