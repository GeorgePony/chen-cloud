package com.czc.cloud.feign.client;

import com.czc.cloud.feign.client.feign.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : chen.zhangchao
 * @apiNote
 * @since 2020/1/8 11:16
 */
@SpringBootApplication
@EnableFeignClients(clients = StudentService.class)
@EnableEurekaClient
@EnableDiscoveryClient
public class FeignClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignClientApplication.class, args);
    }

}
