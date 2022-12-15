package com.ithuipu.reggie.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ithuipu.reggie.entity.SetmealDish;
import com.ithuipu.reggie.mapper.SetmealDtoMapper;
import com.ithuipu.reggie.service.SetmealDtoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @className: SetmealDtoServiceImpl
 * @author: Mr.BingYu
 * @description:
 * @date: 2022/12/15 11:44
 **/

@Service
@Slf4j
public class SetmealDtoServiceImpl extends ServiceImpl<SetmealDtoMapper, SetmealDish> implements SetmealDtoService {
}
