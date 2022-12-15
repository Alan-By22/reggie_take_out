package com.ithuipu.reggie.entity;

import lombok.Data;

import java.util.List;

/**
 * @className: SetmealDto
 * @author: Mr.BingYu
 * @description:
 * @date: 2022/12/15 11:39
 **/

@Data
public class SetmealDto extends Setmeal {
    /**
     * 套餐关联的菜品集合
     */
    private List<SetmealDish> setmealDishes;

    /**
     * 分类名称
     */
    private String categoryName;
}
