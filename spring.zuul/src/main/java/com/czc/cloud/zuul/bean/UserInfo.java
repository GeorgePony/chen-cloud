package com.czc.cloud.zuul.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : chen.zhangchao
 * @apiNote
 * @since 2020/1/7 10:32
 */
@Data
@AllArgsConstructor
public class UserInfo {


    private Integer id;

    private String name;

    private String email;



    private String password;
}
