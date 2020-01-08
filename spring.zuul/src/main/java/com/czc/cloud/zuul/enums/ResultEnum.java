package com.czc.cloud.zuul.enums;

/**
 * @author : chen.zhangchao
 * @apiNote
 * @since 2020/1/7 10:31
 */
public enum ResultEnum {


    EMAIL_PASSWORD_EMPTY(41, "邮箱密码为空"),


    PASSWORD_ERROR(43 , "密码错误"),

    EMAIL_NOT_EXIST(42 , "邮箱不存在");
;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private Integer code;

    private String msg;
}
