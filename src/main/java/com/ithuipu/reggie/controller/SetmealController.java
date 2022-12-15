package com.ithuipu.reggie.controller;

import com.ithuipu.reggie.common.R;
import com.ithuipu.reggie.entity.SetmealDto;
import com.ithuipu.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: SetmealController
 * @author: Mr.BingYu
 * @description:
 * @date: 2022/12/15 11:47
 **/

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {

    private SetmealService setmealService;

    /**请求方式POST
     * 请求路径/setmeal
     * 请求参数json格式数据
     * 请求网址: http://localhost:8080/setmeal
     * 请求方法: POST
     *
     * 保存
     * */
    public R<String> save(@RequestBody SetmealDto setmealDto){
        log.info("套餐信息:{}",setmealDto);

        setmealService.saveWithDish(setmealDto);

        return R.success("新增套餐成功");
    }
}
