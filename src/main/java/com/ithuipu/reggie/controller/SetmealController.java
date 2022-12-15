package com.ithuipu.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ithuipu.reggie.common.R;
import com.ithuipu.reggie.entity.Category;
import com.ithuipu.reggie.entity.Setmeal;
import com.ithuipu.reggie.entity.SetmealDto;
import com.ithuipu.reggie.service.CategoryService;
import com.ithuipu.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: SetmealController
 * @author: Mr.BingYu
 * @description:
 * @date: 2022/12/15 11:47
 **/

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {

    /**
     * 注入service
     */
    @Autowired
    private SetmealService setmealService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 请求方式POST
     * 请求路径/setmeal
     * 请求参数json格式数据
     * 请求网址: http://localhost:8080/setmeal
     * 请求方法: POST
     * <p>
     * 保存
     */
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        log.info("套餐信息:{}", setmealDto);

        setmealService.saveWithDish(setmealDto);

        return R.success("新增套餐成功");
    }

    /**
     * 请求方式GET
     * 请求路径/setmeal/page
     * 请求参数?page=1&pageSize=10&name=xxx
     * <p>
     * 套餐分页查询
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        Page<SetmealDto> dtoPage = new Page<>();
        //分页构造器
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件,根据name模糊查询
        lambdaQueryWrapper.like(name != null, Setmeal::getName, name);
        //添加排序条件
        lambdaQueryWrapper.orderByDesc(Setmeal::getUpdateTime);

        setmealService.page(pageInfo, lambdaQueryWrapper);

        //对象的拷贝
        BeanUtils.copyProperties(pageInfo, dtoPage, "records");
        List<Setmeal> records = pageInfo.getRecords();

        List<SetmealDto> list = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            //拷贝
            BeanUtils.copyProperties(item, setmealDto);
            //分类id
            Long categoryId = item.getCategoryId();
            //根据分类id查询
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                //分类名称
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;
        }).collect(Collectors.toList());

        dtoPage.setRecords(list);

        return R.success(dtoPage);
    }
}
