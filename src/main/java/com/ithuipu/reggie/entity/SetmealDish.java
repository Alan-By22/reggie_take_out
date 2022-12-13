package com.ithuipu.reggie.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;



/**
 * @className: SetmealDish
 * @author: Mr.BingYu
 * @description: 
 * @date: 2022/12/13 11:10
 **/

    

/**
    * 套餐菜品关系
    */
@Data
@TableName(value = "setmeal_dish")
public class SetmealDish {
    /**
     * 主键
     */
    @TableField(value = "id")
    private Long id;

    /**
     * 套餐id 
     */
    @TableField(value = "setmeal_id")
    private String setmealId;

    /**
     * 菜品id
     */
    @TableField(value = "dish_id")
    private String dishId;

    /**
     * 菜品名称 （冗余字段）
     */
    @TableField(value = "name")
    private String name;

    /**
     * 菜品原价（冗余字段）
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 份数
     */
    @TableField(value = "copies")
    private Integer copies;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user",fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 修改人
     */
    @TableField(value = "update_user",fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    /**
     * 是否删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    public static final String COL_ID = "id";

    public static final String COL_SETMEAL_ID = "setmeal_id";

    public static final String COL_DISH_ID = "dish_id";

    public static final String COL_NAME = "name";

    public static final String COL_PRICE = "price";

    public static final String COL_COPIES = "copies";

    public static final String COL_SORT = "sort";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_USER = "create_user";

    public static final String COL_UPDATE_USER = "update_user";

    public static final String COL_IS_DELETED = "is_deleted";
}