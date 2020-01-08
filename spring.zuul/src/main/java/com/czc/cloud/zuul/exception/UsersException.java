package com.czc.cloud.zuul.exception;

import com.czc.cloud.zuul.enums.ResultEnum;

/**
 * @author : chen.zhangchao
 * @apiNote
 * @since 2020/1/7 10:34
 */
public class UsersException extends org.omg.CORBA.UserException{


    public UsersException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
    }
}
