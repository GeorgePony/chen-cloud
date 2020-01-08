package com.czc.cloud.zuul.service.impl;

import com.czc.cloud.zuul.bean.UserInfo;
import com.czc.cloud.zuul.enums.ResultEnum;
import com.czc.cloud.zuul.exception.UsersException;
import com.czc.cloud.zuul.service.LoginService;
import com.czc.cloud.zuul.util.KeyUtils;
import com.czc.cloud.zuul.util.RedisConsts;
import com.czc.cloud.zuul.vo.UserInfoDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : chen.zhangchao
 * @apiNote
 * @since 2020/1/7 10:28
 */
@Service
public class LoginServiceImpl implements LoginService {


    private static List<UserInfo> userList = new ArrayList<>();


    @Autowired
    StringRedisTemplate stringRedisTemplate;


    private UserInfo findUserByEmail(String email){
        for(UserInfo userInfo : userList){
            if(userInfo.getEmail().equalsIgnoreCase(email)){
                return userInfo;
            }
        }
        return null;
    }


    @Override
    public UserInfoDTO loginByEmail(String email, String password) throws UsersException{

        if (
                userList.isEmpty()
        ) {
            userList.add(new UserInfo(1 , "chen" , "czc@163.com" , "123"));

        }

        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            throw new UsersException(ResultEnum.EMAIL_PASSWORD_EMPTY);
        }

        UserInfo user = findUserByEmail(email);
        if (user == null) {
            throw new UsersException(ResultEnum.EMAIL_NOT_EXIST);
        }
        if (!user.getPassword().equals(password)) {
            throw new UsersException(ResultEnum.PASSWORD_ERROR);
        }

        //生成 token 并保存在 Redis 中
        String token = KeyUtils.genUniqueKey();
        //将token存储在 Redis 中。键是 TOKEN_用户id, 值是token
        stringRedisTemplate.opsForValue().set(String.format(RedisConsts.TOKEN_TEMPLATE
                , user.getId()), token, 2L, TimeUnit.HOURS);

        UserInfoDTO dto = new UserInfoDTO();
        BeanUtils.copyProperties(user, dto);
        dto.setToken(token);

        return dto;
    }
}
