package com.czc.cloud.sccclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chen.zhangchao
 * @apiNote
 * @since 2019/9/27 23:10
 */
@SpringBootApplication
@RestController
@EnableEurekaClient
public class Application {


    @Value("${guestname}")
    String name;

    @RequestMapping("/")
    public String sayHello(){
        return "Hello" + name;
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class ,args);
    }
}
