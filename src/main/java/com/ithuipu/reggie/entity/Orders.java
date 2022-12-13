package com.ithuipu.reggie.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;



/**
 * @className: Orders
 * @author: Mr.BingYu
 * @description: 
 * @date: 2022/12/13 11:10
 **/

    

/**
    * 订单表
    */
@Data
@TableName(value = "orders")
public class Orders {
    /**
     * 主键
     */
    private Long id;

    /**
     * 订单号
     */
    @TableField(value = "number")
    private String number;

    /**
     * 订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 下单用户
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 地址id
     */
    @TableField(value = "address_book_id")
    private Long addressBookId;

    /**
     * 下单时间
     */
    @TableField(value = "order_time")
    private Date orderTime;

    /**
     * 结账时间
     */
    @TableField(value = "checkout_time")
    private Date checkoutTime;

    /**
     * 支付方式 1微信,2支付宝
     */
    @TableField(value = "pay_method")
    private Integer payMethod;

    /**
     * 实收金额
     */
    @TableField(value = "amount")
    private BigDecimal amount;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "address")
    private String address;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "consignee")
    private String consignee;

    public static final String COL_ID = "id";

    public static final String COL_NUMBER = "number";

    public static final String COL_STATUS = "status";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_ADDRESS_BOOK_ID = "address_book_id";

    public static final String COL_ORDER_TIME = "order_time";

    public static final String COL_CHECKOUT_TIME = "checkout_time";

    public static final String COL_PAY_METHOD = "pay_method";

    public static final String COL_AMOUNT = "amount";

    public static final String COL_REMARK = "remark";

    public static final String COL_PHONE = "phone";

    public static final String COL_ADDRESS = "address";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_CONSIGNEE = "consignee";
}