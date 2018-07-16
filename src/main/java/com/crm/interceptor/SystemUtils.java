package com.crm.interceptor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;

public class SystemUtils {

    public static final String STATIC_NO_PERMISSION_PATH =
            ".*/((login)|(logout)|(hello)|(mvc)|(app)|(weixin)|(static)|(main)|(websocket)).*";
    public static final String PERMISSION_NAME = "perssionName";

    public static String getMethodOfPermission(HandlerMethod handler){
        String permissionName = handler.getMethodAnnotation(RequestMapping.class).name();
        if(permissionName.equals("")){
            return null;
        }
        String classPermissionURL = handler.getBeanType().getAnnotation(RequestMapping.class).value()[0];
        String methodPermissionURL = handler.getMethodAnnotation(RequestMapping.class).value()[0];
        return (classPermissionURL + ":" + methodPermissionURL).replace("/","");
    }
}
