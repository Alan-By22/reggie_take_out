package com.ithuipu.reggie.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装Dish的flavors
 */
@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    /**菜品分类名称*/
    private String categoryName;

    private Integer copies;
}
