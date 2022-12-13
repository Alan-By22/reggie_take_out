package com.ithuipu.reggie.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



/**
 * @className: User
 * @author: Mr.BingYu
 * @description: 
 * @date: 2022/12/13 11:10
 **/

    

/**
    * 用户信息
    */
@Data
@TableName(value = "`user`")
public class User {
    /**
     * 主键
     */
    @TableField(value = "id")
    private Long id;

    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 性别
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 身份证号
     */
    @TableField(value = "id_number")
    private String idNumber;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 状态 0:禁用，1:正常
     */
    @TableField(value = "status")
    private Integer status;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_PHONE = "phone";

    public static final String COL_SEX = "sex";

    public static final String COL_ID_NUMBER = "id_number";

    public static final String COL_AVATAR = "avatar";

    public static final String COL_STATUS = "status";
}