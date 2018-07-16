package com.crm.interceptor;

import com.crm.interceptor.exception.NoLoginException;
import com.crm.interceptor.exception.NoPermissionException;
import com.crm.interceptor.token.JSON_WEB_TOKEN;
import com.crm.interceptor.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class LoginSystemPermission extends HandlerInterceptorAdapter {

    @Autowired
    Token token;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws NoLoginException, NoPermissionException {

        String url = request.getServletPath();
        if (url.matches(SystemUtils.STATIC_NO_PERMISSION_PATH)) {
            return true;
        }

        String json = request.getParameter("token");
        JSON_WEB_TOKEN userToken = null;
        try {
            userToken = token.unCreateToken(JSON_WEB_TOKEN.class, json);
        } catch (UnsupportedEncodingException e) {
            throw new NoLoginException();
        }
        String userPermission = userToken.getPermissions().toString();
        if (userPermission == null) {
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
