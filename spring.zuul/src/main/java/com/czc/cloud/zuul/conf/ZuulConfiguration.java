package com.czc.cloud.zuul.conf;

import com.czc.cloud.zuul.filter.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : chen.zhangchao
 * @apiNote
 * @since 2020/1/7 12:55
 */
@Configuration
public class ZuulConfiguration {


    @Bean
    public AuthFilter initFilter(){
        return new AuthFilter();
    }
}
