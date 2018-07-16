package com.crm.utils;

//import com.google.gson.Gson;
//import com.hanyong.pojo.LogMessage;
//import com.hanyong.service.EmpService;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Aspect
//@Component
//@Order(1)
//public class LogAsp {
//    @Autowired
//    private EmpService empService;
//
//    @Around("@annotation(com.hanyong.util.SystemLog)")
//    public Object show(ProceedingJoinPoint joinPoint) {
//        LogMessage logMessage = new LogMessage();
//        Class clazz = joinPoint.getTarget().getClass();
//        Object[] objects = joinPoint.getArgs();
//        Gson gson = new Gson();
//        String argus = "";
////        if (objects.length != 0) {
////            argus = gson.toJson(objects);
////        }
//        String methodName = joinPoint.getSignature().getName();
//        Method[] methods = clazz.getMethods();
//        Object object = null;
//        Long startTime = System.currentTimeMillis();
//        String startNow = timeNow();
//        for (Method m : methods) {
//            if (methodName.equals(m.getName())) {
//                if (objects.length == m.getParameterTypes().length) {
//                   if(m.getAnnotation(SystemLog.class).isWrite()){
//                       argus = gson.toJson(objects);
//                   }else{
//                       for (int i = 0; i < objects.length; i++) {
//                           argus += objects[i].getClass().getTypeName() + ",";
//                       }
//                   }
//                    logMessage.setStartdate(startNow);
//                    logMessage.setMethodname(methodName);
//                    logMessage.setDesciption(m.getAnnotation(SystemLog.class).description());
//                    try {
//                        object = joinPoint.proceed(objects);
//                        logMessage.setStatus("成功");
//                    } catch (Throwable throwable) {
//                        throwable.printStackTrace();
//                        logMessage.setStatus("失败");
//                        logMessage.setException(throwable.toString());
//                    }
//                    Long endTime = System.currentTimeMillis();
//                    String time = endTime - startTime + "ms";
//                    String endNow = timeNow();
//                    logMessage.setArgus(argus);
//                    logMessage.setEnddate(endNow);
//                    logMessage.setTime(time);
//                    logMessage.setUsername("han");
//                    logMessage.setRoles("超级管理员");
//                    empService.insertLog(logMessage);
//                }
//
//            }
//
//        }
//        return object;
//    }
//
//    private static String timeNow() {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        return df.format(new Date());// new Date()为获取当前系统时间
//    }
//
//}
