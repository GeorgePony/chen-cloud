package com.czc.cloud.zuul.service;

import com.czc.cloud.zuul.exception.UsersException;
import com.czc.cloud.zuul.vo.UserInfoDTO;

/**
 * @author : chen.zhangchao
 * @apiNote
 * @since 2020/1/7 10:27
 */
public interface LoginService {

    public UserInfoDTO loginByEmail(String email, String password) throws UsersException;

}
