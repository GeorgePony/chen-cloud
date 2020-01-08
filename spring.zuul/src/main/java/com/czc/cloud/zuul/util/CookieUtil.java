package com.czc.cloud.zuul.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author : chen.zhangchao
 * @apiNote
 * @since 2020/1/7 10:17
 */
public class CookieUtil {



    public static Cookie getCookieByName(HttpServletRequest request , String name){
        Cookie[] cookies = request.getCookies();
        for (int i = cookies.length - 1; i >= 0; i--) {
            Cookie coo = cookies[i];
            if(coo.getName().equalsIgnoreCase(name)){
                return coo;
            }
        }
        return null;
    }
}
