package com.ithuipu.reggie.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ithuipu.reggie.entity.Category;
import com.ithuipu.reggie.mapper.CategoryMapper;
import com.ithuipu.reggie.service.CategoryService;
import org.springframework.stereotype.Service;


/**
 * @className: CategoryServiceImpl
 * @author: Mr.BingYu
 * @description: 
 * @date: 2022/12/12 23:54
 **/

    

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{

}
