package com.ithuipu.reggie.controller;

import com.ithuipu.reggie.common.R;
import com.ithuipu.reggie.config.QiniuUtils;
import com.ithuipu.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @className: CommonController
 * @author: Mr.BingYu
 * @description:
 * @date: 2022/12/13 23:46
 **/

/**
 * 文件的上传和下载
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    /**
     * 注入service
     */
    @Autowired
    private CategoryService categoryService;
    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {
        log.info("file:...." + file.getOriginalFilename());
        try {
            //1.解决重名问题
            String originalFilename = file.getOriginalFilename();
            //获得后缀
            int i = originalFilename.lastIndexOf(".");
            //切割        获得.jpg
            String substring = originalFilename.substring(i - 1);
            //使用uuid
            String fileName = UUID.randomUUID().toString() + substring;
            //2.使用七牛云工具类
            QiniuUtils.upload2Qiniu(file.getBytes(), fileName);
            //需要返回文件名，以便保存到数据库
            return R.success(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.error("文件上传失败！");
    }


}
