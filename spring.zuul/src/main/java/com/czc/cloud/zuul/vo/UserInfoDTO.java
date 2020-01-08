package com.czc.cloud.zuul.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author : chen.zhangchao
 * @apiNote
 * @since 2020/1/7 10:30
 */
@Data
@ToString
public class UserInfoDTO {

    private String token;

    private String name;

    private String email;

    private Integer id;


    private String password;
}
