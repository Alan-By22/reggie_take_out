package com.ithuipu.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ithuipu.reggie.entity.Category;
import org.apache.ibatis.annotations.Mapper;


/**
 * @className: CategoryMapper
 * @author: Mr.BingYu
 * @description: 
 * @date: 2022/12/12 23:54
 **/


@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}