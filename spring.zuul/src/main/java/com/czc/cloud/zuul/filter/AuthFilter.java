package com.czc.cloud.zuul.filter;

import com.czc.cloud.zuul.util.CookieUtil;
import com.czc.cloud.zuul.util.RedisConsts;
import com.czc.cloud.zuul.vo.ResultVO;
import com.google.gson.Gson;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author : chen.zhangchao
 * @apiNote
 * @since 2020/1/7 10:03
 */
@Slf4j
public class AuthFilter extends ZuulFilter {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //排除过滤的 uri 地址
    private static final String LOGIN_URI = "/api/user/login";
    private static final String LOGOUT_URI = "/api/user/logout";

    //无权限时的提示语
    private static final String INVALID_TOKEN = "invalid token";
    private static final String INVALID_USERID = "invalid userId";

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER  - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        log.info("uri:{}", request.getRequestURI());
        //登录和登出接口不拦截，其他接口都要拦截校验 token
        if (LOGIN_URI.equals(request.getRequestURI()) ||
                LOGOUT_URI.equals(request.getRequestURI())) {
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        Cookie tokenCookie = CookieUtil.getCookieByName(request, "token");
        if (tokenCookie == null || StringUtils.isEmpty(tokenCookie.getValue())) {
            readTokenFromHeader(requestContext, request);
        } else {
            verifyToken(requestContext, request, tokenCookie.getValue());
        }

        return null;
    }

    private void readTokenFromHeader(RequestContext requestContext, HttpServletRequest request) {
        //从 header 中读取
        String headerToken = request.getHeader("token");
        if (StringUtils.isEmpty(headerToken)) {
            setUnauthorizedResponse(requestContext, INVALID_TOKEN);
        } else {
            verifyToken(requestContext, request, headerToken);
        }
    }

    /**
     * 设置 401 无权限状态
     */
    private void setUnauthorizedResponse(RequestContext requestContext, String msg) {
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());

        ResultVO vo = new ResultVO();
        vo.setCode(401);
        vo.setMsg(msg);
        Gson gson = new Gson();
        String result = gson.toJson(vo);

        requestContext.setResponseBody(result);
    }

    private void verifyToken(RequestContext requestContext, HttpServletRequest request, String token) {
        //需要从cookie或header 中取出 userId 来校验 token 的有效性，因为每个用户对应一个token，在Redis中是以 TOKEN_userId 为键的
        Cookie userIdCookie = CookieUtil.getCookieByName(request, "userId");
        if (userIdCookie == null || StringUtils.isEmpty(userIdCookie.getValue())) {
            //从header中取userId
            String userId = request.getHeader("userId");
            if (StringUtils.isEmpty(userId)) {
                setUnauthorizedResponse(requestContext, INVALID_USERID);
            } else {
                String redisToken = stringRedisTemplate.opsForValue().get(String.format(RedisConsts.TOKEN_TEMPLATE, userId));
                if (StringUtils.isEmpty(redisToken) || !redisToken.equals(token)) {
                    setUnauthorizedResponse(requestContext, INVALID_TOKEN);
                }
            }
        } else {
            String redisToken = stringRedisTemplate.opsForValue().get(String.format(RedisConsts.TOKEN_TEMPLATE, userIdCookie.getValue()));
            if (StringUtils.isEmpty(redisToken) || !redisToken.equals(token)) {
                setUnauthorizedResponse(requestContext, INVALID_TOKEN);
            }
        }
    }
}
