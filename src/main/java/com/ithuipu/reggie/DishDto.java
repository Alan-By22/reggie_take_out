package com.ithuipu.reggie;

import com.ithuipu.reggie.entity.Dish;
import com.ithuipu.reggie.entity.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装Dish的flavors
 */
@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
