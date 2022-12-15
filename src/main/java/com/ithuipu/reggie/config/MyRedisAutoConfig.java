package com.ithuipu.reggie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;



/**
 * @className: MyRedisAutoConfig
 * @author: Mr.BingYu
 * @description:
 * @date: 2022/12/16 0:07
 **/

@Configuration
public class MyRedisAutoConfig {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer()); //key的序列号器
      //template.setValueSerializer(new GenericJackson2JsonRedisSerializer()); //值序列号器
        return template;
    }
}
