package com.crm.view;

import com.crm.pojo.Page;
import com.crm.pojo.Permissions;
import com.crm.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/permission")
public class PermissionsConrorller {
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    private PermissionsService permissionsService;


    /**
     * 查询所有权限
     *
     * @param page
     * @param limit
     * @return Page 查询所有权限返回分页数据
     */
    @GetMapping(value = "/queryAllpermissions", name = "查询所有权限")
    public Page queryAllpermissions(Integer page, Integer limit) {
        return permissionsService.queryAllpermissions(page, limit);
    }


    /**
     * 更新系统权限
     *
     * @return String 返回更新权限条数
     */
    @GetMapping(value = "/systemPermission", name = "更新系统权限")
    public String updateSystemPermission() {
        Integer n = modifySystemPermission();
        return "系统更新了" + n + "条权限";
    }

    /**
     * 更新系统权限
     *
     * @return int 返回更新条数
     */
    private synchronized int modifySystemPermission() {
        //获取当前已存在的所有的权限
        List<String> permissionList = permissionsService.queryAll();
        //创建权限集合
        List<Permissions> permissions = new ArrayList<>();
        Map<RequestMappingInfo, HandlerMethod> mappingInfoHandlerMethodMap =
                requestMappingHandlerMapping.getHandlerMethods();
        //得到所有被requestMapping所修饰的方法
        Collection<HandlerMethod> handlerMethods = mappingInfoHandlerMethodMap.values();
        if (handlerMethods == null || handlerMethods.isEmpty()) {
            return 0;
        }
        for (HandlerMethod handlerMethod : handlerMethods) {
            Permissions permissiontb = new Permissions();
            RequestMapping methodRequestMapping =
                    handlerMethod.getMethodAnnotation(RequestMapping.class);
            if (!methodRequestMapping.name().equals((""))) {
                String methodURL = methodRequestMapping.value()[0];
                RequestMapping classRequestMapping =
                        handlerMethod.getBeanType().getAnnotation(RequestMapping.class);
                String classURL = classRequestMapping.value()[0];
                String module = "".equals(classRequestMapping.name()) ? "" : classRequestMapping.name();
                String permissionURL = (classURL + ":" + methodURL).replace("/", "");
                if (permissionList != null) {
                    if (permissionList.contains(permissionURL)) {
                        continue;
                    }
                }
                permissiontb.setPermissionname(methodRequestMapping.name());
                permissiontb.setPermissionvalue(permissionURL);
                permissiontb.setPermissionmodule(module);
                permissions.add(permissiontb);
            }
        }
        if (permissions.size() != 0) {
            return permissionsService.insertSystemPermission(permissions);
        } else {
            return 0;
        }

    }
}
