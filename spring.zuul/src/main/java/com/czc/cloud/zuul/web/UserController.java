package com.czc.cloud.zuul.web;

import com.alibaba.fastjson.JSONObject;
import com.czc.cloud.zuul.exception.UsersException;
import com.czc.cloud.zuul.service.LoginService;
import com.czc.cloud.zuul.vo.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chen.zhangchao
 * @apiNote
 * @since 2020/1/7 10:44
 */
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private LoginService loginService;


    @GetMapping("login")
    public String login(@RequestParam("email") String email ,
                        @RequestParam("password") String password){
        try {
            UserInfoDTO user = loginService.loginByEmail(email , password);
            return JSONObject.toJSONString(user);
        } catch (UsersException e) {
            log.error(e.getLocalizedMessage() , e);
            return e.getMessage();
        }
    }
    @GetMapping("app")
    public String app(){
        return "app";
    }




}
