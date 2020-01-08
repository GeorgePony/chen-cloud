package com.czc.cloud.scc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author : chen.zhangchao
 * @apiNote springConfig 主启动类
 * @since 2019/9/27 22:40
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class Application {

    /**
     * 示例程序  启动demo页面： http://localhost:8081/client/dev
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class , args);
    }
}
