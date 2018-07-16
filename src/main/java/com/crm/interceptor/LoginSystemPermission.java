package com.crm.interceptor;

import com.crm.interceptor.exception.NoLoginException;
import com.crm.interceptor.exception.NoPermissionException;
import com.crm.interceptor.token.JSON_WEB_TOKEN;
import com.crm.interceptor.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class LoginSystemPermission extends HandlerInterceptorAdapter {

    @Autowired
    Token token;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws NoLoginException, NoPermissionException {

        String url = request.getServletPath();
        if (url.matches(SystemUtils.STATIC_NO_PERMISSION_PATH)) {
            return true;
        }
        String json = request.getParameter("token");
        if (json == null || json.equals("")) {
            throw new NoLoginException("对不起,还没登录401");
        }
        JSON_WEB_TOKEN userToken = null;
        String userPermission = "";
        try {
            userToken = token.unCreateToken(JSON_WEB_TOKEN.class, json);
            if (userToken != null) {
                userPermission = userToken.getPermissions().toString();
                String redisToken = (String) redisTemplate.opsForValue().get(userToken.getLoginname());
                if (redisToken == null || !redisToken.equals(json) || userPermission == null) {
                    throw new NoPermissionException("对不起,还没登录402");
                }
            }
        } catch (UnsupportedEncodingException e) {
            throw new NoLoginException();
        }

        if (handler instanceof HandlerMethod) {
            String permissionURL = SystemUtils.getMethodOfPermission((HandlerMethod) handler);
            if (!userPermission.contains(permissionURL)) {
                throw new NoPermissionException();
            }
        }
        return true;
    }
}
