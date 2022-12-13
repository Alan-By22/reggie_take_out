package com.ithuipu.reggie.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



/**
 * @className: Category
 * @author: Mr.BingYu
 * @description: 
 * @date: 2022/12/12 23:54
 **/

    

/**
    * 菜品及套餐分类
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "category")
public class Category {
    /**
     * 主键
     */
    private Long id;

    /**
     * 类型   1 菜品分类 2 套餐分类
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 分类名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 顺序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

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

    public static final String COL_ID = "id";

    public static final String COL_TYPE = "type";

    public static final String COL_NAME = "name";

    public static final String COL_SORT = "sort";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_USER = "create_user";

    public static final String COL_UPDATE_USER = "update_user";
}