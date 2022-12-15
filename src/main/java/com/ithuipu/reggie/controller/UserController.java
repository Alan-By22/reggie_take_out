package com.ithuipu.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ithuipu.reggie.common.R;
import com.ithuipu.reggie.config.SMSUtils;
import com.ithuipu.reggie.config.ValidateCodeUtils;
import com.ithuipu.reggie.entity.User;
import com.ithuipu.reggie.service.UserService;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @className: UserController
 * @author: Mr.BingYu
 * @description:
 * @date: 2022/12/15 21:52
 **/

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    /**
     * 注入service
     */
    @Autowired
    private UserService userService;

    /**
     * 注入redis
     */
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 请求方式POST请求路径/user/sendMsg请求参数{"phone":"13100001111"
     * 发送手机短信验证码
     * }
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        //获取手机号
        String phone = user.getPhone();
        if (StringUtils.isNotEmpty(phone)) {
            //随机生成的4为验证码
            Integer integerCode = ValidateCodeUtils.generateValidateCode(6);
            String code = integerCode.toString();

            log.info("验证码的code={}", code);
            //使用腾讯云工具
            try {
                SMSUtils.sendShortMessage(SMSUtils.TEMPLATE_SEND_ID, phone, code);
            } catch (TencentCloudSDKException e) {
                e.printStackTrace();
            }
            //把验证码存起来  这里使用session来存放验证码，当然也可以存到redis
            //session.setAttribute(phone, code);
            //存入到redis
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return R.success("手机验证码发送成功");
        }
        return R.error("手机验证码发送失败");
    }


    /**
     * 请求网址: http://localhost:8080/user/login
     * 请求方法: POST
     * <p>
     * 移动端的登陆
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session) {
        log.info(map.toString());

        //获取手机号
        String phone = map.get("phone").toString();
        //获取验证码
        String code = map.get("code").toString();
        //从session中获取保存的验证码
        //Object codeInSession = session.getAttribute(phone);
        //从redis中获取保存的验证码
        Object codeInRedis = redisTemplate.opsForValue().get(phone);
        //进行验证码的比对
        if (codeInRedis != null && codeInRedis.equals(code)) {
            //如果对比成功,说明登陆成功
            LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(User::getPhone, phone);

            User user = userService.getOne(lambdaQueryWrapper);
            if (user == null) {
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            //登陆成功删除验证码
            redisTemplate.delete(phone);
            session.setAttribute("user", user.getId());
            return R.success(user);
        }
        return R.error("登陆失败");
    }
}
