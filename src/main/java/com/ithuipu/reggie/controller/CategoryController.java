package com.ithuipu.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ithuipu.reggie.common.R;
import com.ithuipu.reggie.entity.Category;
import com.ithuipu.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @className: CategoryController
 * @author: Mr.BingYu
 * @description:
 * @date: 2022/12/12 23:55
 **/

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    /**page/category/list.html*/

    /**
     * 注入service
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * http://localhost:8080/category/page?page=1&pageSize=10
     * 请求方法: GET
     * page/category/list.html
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {
        log.info("page = {},pageSize = {}", page, pageSize);
        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();

        //添加排序条件
        queryWrapper.orderByDesc(Category::getUpdateTime);

        //执行查询
        categoryService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 请求方式POST
     * 请求路径/category请求参数json格式 - {"name":"川菜","type":"1","sort":2}
     * <p>
     * 新增分类
     */
    @PostMapping
    public R<String> save(@RequestBody Category category) {
        log.info("category:{}", category);

        categoryService.save(category);
        return R.success("新增分类成功");
    }
}
