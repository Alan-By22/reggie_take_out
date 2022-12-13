package com.ithuipu.reggie.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;



/**
 * @className: Setmeal
 * @author: Mr.BingYu
 * @description: 
 * @date: 2022/12/13 11:10
 **/

    

/**
    * 套餐
    */
@Data
@TableName(value = "setmeal")
public class Setmeal {
    /**
     * 主键
     */
    private Long id;

    /**
     * 菜品分类id
     */
    @TableField(value = "category_id")
    private Long categoryId;

    /**
     * 套餐名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 套餐价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 状态 0:停用 1:启用
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 编码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 描述信息
     */
    @TableField(value = "description")
    private String description;

    /**
     * 图片
     */
    @TableField(value = "image")
    private String image;

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

    public static final String COL_CATEGORY_ID = "category_id";

    public static final String COL_NAME = "name";

    public static final String COL_PRICE = "price";

    public static final String COL_STATUS = "status";

    public static final String COL_CODE = "code";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_IMAGE = "image";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_CREATE_USER = "create_user";

    public static final String COL_UPDATE_USER = "update_user";

    public static final String COL_IS_DELETED = "is_deleted";
}