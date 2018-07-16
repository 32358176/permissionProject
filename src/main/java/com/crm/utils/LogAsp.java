package com.crm.utils;

import com.crm.dao.mysql.LogmessageMapper;
import com.crm.dao.mysql.UsersMapper;
import com.crm.pojo.mysql.Logmessage;
import com.crm.utils.data.DataSourceUtils;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Aspect
@Component
public class LogAsp {

    @Autowired
    private LogmessageMapper logmessageMapper;
    @Autowired
    HttpSession session;
    @Autowired
    private UsersMapper usersMapper;

    @Pointcut("execution(public * com.crm.service.oracle..*(..))")
    public void oracleCut() {
        // 系统中需要配置oracle数据源的切面
    }

    @Before("oracleCut()")
    @Order(10)
    public void dataAround() {
        DataSourceUtils.setDataSourceKey(TYPE.ORACLE);
    }

    @Around("@annotation(com.crm.utils.SystemLog)")
    @Order(20)
    public Object show(ProceedingJoinPoint joinPoint) {
        Logmessage logMessage = new Logmessage();
        Class clazz = joinPoint.getTarget().getClass();
        Object[] objects = joinPoint.getArgs();
        Gson gson = new Gson();
        String argus = "";
        String methodName = joinPoint.getSignature().getName();
        Method[] methods = clazz.getMethods();
        Object object = null;
        Long startTime = System.currentTimeMillis();
        String startNow = timeNow();
        for (Method m : methods) {
            if (methodName.equals(m.getName())) {
                if (objects.length == m.getParameterTypes().length) {
                    if (m.getAnnotation(SystemLog.class) != null) {
                        if (m.getAnnotation(SystemLog.class).isWrite()) {
                            argus = gson.toJson(objects);
                        } else {
                            for (int i = 0; i < objects.length; i++) {
                                argus += objects[i].getClass().getTypeName() + ",";
                            }
                        }
                        logMessage.setStartdatetime(startNow);
                        logMessage.setMethodname(methodName);
                        logMessage.setDesciption(m.getAnnotation(SystemLog.class).description());
                        try {
                            object = joinPoint.proceed(objects);
                            logMessage.setStatus("成功");
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                            logMessage.setStatus("失败");
                            logMessage.setException(throwable.toString());
                        }
                        Long endTime = System.currentTimeMillis();
                        String time = endTime - startTime + "ms";
                        String endNow = timeNow();
                        logMessage.setArgus(argus);
                        logMessage.setEnddatetime(endNow);
                        logMessage.setTime(time);
                        Integer userId = (Integer) session.getAttribute("userId");
                        System.out.println(userId+"-------------------");
                        String username = "";
                        List<String> roles = new ArrayList<>();
                        if (userId != null) {
                            username = usersMapper.selectUsernameByUserId(userId);
                            roles = usersMapper.selectRolesByUserId(userId);
                        }
                        logMessage.setUsername(username);
                        logMessage.setRoles(roles.toString());
                        logmessageMapper.insertSelective(logMessage);
                    }

                }
            }

        }
        return object;
    }

    private static String timeNow() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

}
