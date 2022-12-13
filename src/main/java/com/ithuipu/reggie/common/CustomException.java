package com.ithuipu.reggie.common;

/**
 * @className: CustomException
 * @author: Mr.BingYu
 * @description:
 * @date: 2022/12/13 16:22
 **/

/**
 * 自定义业务异常类
 */
public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
