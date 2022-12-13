package com.ithuipu.reggie.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;



/**
 * @className: DishFlavor
 * @author: Mr.BingYu
 * @description: 
 * @date: 2022/12/13 11:09
 **/

    

/**
    * 菜品口味关系表
    */
@Data
@TableName(value = "dish_flavor")
public class DishFlavor {
    /**
     * 主键
     */
    @TableField(value = "id")
    private Long id;

    /**
     * 菜品
     */
    @TableField(value = "dish_id")
    private Long dishId;

    /**
     * 口味名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 口味数据list
     */
    @TableField(value = "value")
    private String value;

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

    public static final String COL_DISH_ID = "dish_id";

    public static final String COL_NAME = "name";

    public static final String COL_VALUE = "value";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_USER = "create_user";

    public static final String COL_UPDATE_USER = "update_user";

    public static final String COL_IS_DELETED = "is_deleted";
}