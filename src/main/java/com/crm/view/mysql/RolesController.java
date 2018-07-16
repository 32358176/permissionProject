package com.crm.view;

import com.crm.pojo.Modules;
import com.crm.pojo.Page;
import com.crm.pojo.Roles;
import com.crm.service.RolesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/roles",name = "角色模块")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    /**
     * 查询所有角色
     * @param page
     * @param limit
     * @return Page 查询所有角色分页信息
     */
    @GetMapping(value = "queryAllRoles",name = "查询所有角色")
    public Page queryAllRoles(Integer page,Integer limit){
        return rolesService.queryAllRoles(page,limit);
    }

    /**
     * 插入新角色
     * @param rolesName
     * @return Map 插入新角色返回信息
     */
    @GetMapping(value = "/insertRolesByRolesName" , name = "插入新角色")
    public Map insertRolesByRolesName(String rolesName){
        return rolesService.insertRolesByRolesName(rolesName);
    }

    /**
     * 根据角色ID删除角色
     * @param rolesId
     * @return Map 根据角色ID删除角色返回信息
     */
    @GetMapping(value = "/delectRolesByRolesId", name = "根据角色ID删除角色")
    public Map delectRolesByRolesId(Integer rolesId){
        return rolesService.delectRolesByRolesId(rolesId);
    }

    /**
     * 修改角色名
     * @param rolesName
     * @param rolesId
     * @return Map 修改角色名返回信息
     */
    @GetMapping(value = "updateRolesNameByRolesName",name = "修改角色名")
    public Map updateRolesNameByRolesName(String rolesName,Integer rolesId){
        return rolesService.updateRolesNameByRolesName(rolesName,rolesId);
    }

    /**
     *编辑角色权限
     * @param rolesId
     * @return Map 编辑角色权限返回信息
     */
    @GetMapping(value = "/rolesPermission",name = "编辑角色权限")
    public Map rolesPermission(Integer rolesId){
        return rolesService.rolesPermission(rolesId);
    }

    /**
     *增加角色权限
     * @param rolesId
     * @param permissionName
     * @return Map 增加角色权限返回信息
     */
    @GetMapping(value = "/insertRolesPermissions",name = "增加角色权限")
    public Map updateRolesPermissions(Integer rolesId,String permissionName){
        return rolesService.insertRolesPermissionByRolesId(rolesId,permissionName);
    }

    /**
     *移除角色权限
     * @param rolesId
     * @param permissionName
     * @return Map 移除角色权限返回信息
     */
    @GetMapping(value = "/removeRolesPermissions",name = "移除角色权限")
    public Map removeRolesPermissions(Integer rolesId,String permissionName){
        return rolesService.removeRolesPermissions(rolesId,permissionName);
    }

    /**
     *查询角色详细信息
     * @param rolesId
     * @return Map 查询角色详细信息返回信息
     */
    @GetMapping(value = "/queryRolesInfo",name = "查询角色详细信息")
    public Map queryRolesInfo(Integer rolesId){
        return rolesService.queryRolesInfo(rolesId);
    }

    /**
     *根据角色ID查询没有模块
     * @param rolesId
     * @return List<Modules> 根据角色ID查询没有模块集合
     */
    @GetMapping(value = "/queryAllNoModulesByRolesId",name = "根据角色ID查询没有模块")
    public List<Modules> queryAllNoModulesByRolesId(Integer rolesId){
        return rolesService.queryAllNoModulesByRolesId(rolesId);
    }

    /**
     * 根据角色ID查询已有模块
     * @param rolesId
     * @return List<Modules> 根据角色ID查询已有模块集合
     */
    @GetMapping(value = "/queryAllModulesByRolesId",name = "根据角色ID查询已有模块")
    public List<Modules> queryAllModulesByRolesId(Integer rolesId){
        return rolesService.queryAllModulesByRolesId(rolesId);
    }

    /**
     * 设定角色模块
     * @param rolesId
     * @param modulesId
     * @return Map 设定角色模块返回信息
     */
    @GetMapping(value = "/insertModulesByRoles",name = "设定角色模块")
    public Map insertModulesByRoles(Integer rolesId,Integer modulesId){
        return rolesService.insertModulesByRoles(rolesId,modulesId);
    }

}
