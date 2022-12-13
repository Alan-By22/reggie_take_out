package com.ithuipu.reggie.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ithuipu.reggie.entity.ShoppingCart;
import com.ithuipu.reggie.mapper.ShoppingCartMapper;
import com.ithuipu.reggie.service.ShoppingCartService;


/**
 * @className: ShoppingCartServiceImpl
 * @author: Mr.BingYu
 * @description: 
 * @date: 2022/12/13 11:10
 **/

    

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService{

}
