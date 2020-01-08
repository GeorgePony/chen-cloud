package com.czc.cloud.eureka.client.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EurekaController {


    @Autowired
    private DiscoveryClient discoveryClient;

    // 访问路径 http://localhost:8771/user-instance
    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo(){

        return discoveryClient.getInstances("spring-config-client");
    }


    @GetMapping("/get-app")
    public String getApp(){
        return "app-eureka-client";
    }
}
