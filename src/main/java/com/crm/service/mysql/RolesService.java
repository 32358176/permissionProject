package com.crm.service.mysql;

import com.crm.dao.mysql.ModulesMapper;
import com.crm.dao.mysql.RolesMapper;
import com.crm.pojo.mysql.Modules;
import com.crm.pojo.mysql.Page;
import com.crm.pojo.mysql.Roles;
import com.crm.utils.SystemLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RolesService {


    @Autowired
    private RolesMapper rolesMapper;

    @Autowired
    private ModulesMapper modulesMapper;

    /**
     * 查询所有角色信息
     *
     * @return 查询所有角色信息返回角色名集合
     */
    @SystemLog(description = "查询所有角色信息")
    public List<String> queryAllRolesName() {
        return rolesMapper.queryAllRolesName();
    }

    /**
     * 根据用户ID查询用户角色ID
     *
     * @param id
     * @return 根据用户ID查询用户角色ID返回角色ID集合
     */
    @SystemLog(description = "根据用户ID查询用户角色ID")
    public List<Integer> queryRolesByUserID(Integer id) {

        return rolesMapper.queryRolesByUserID(id);

    }

    /**
     * 根据角色查询用户
     *
     * @param userId
     * @return 根据角色查询用户返回用户名集合
     */
    @SystemLog(description = "根据角色查询用户")
    public List<String> queryUserNoRoles(Integer userId) {
        return rolesMapper.queryUserNoRoles(userId);
    }

    /**
     * 查询所有角色
     *
     * @param page
     * @param limit
     * @return 查询所有角色返回分页信息
     */
    @SystemLog(description = "查询所有角色")
    public Page queryAllRoles(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Roles> roles = rolesMapper.queryAllRoles();
        return new Page(new PageInfo(roles));
    }

    /**
     * 创建新角色
     *
     * @param rolesName
     * @return 创建新角色返回信息
     */
    @SystemLog(description = "创建新角色")
    public Map insertRolesByRolesName(String rolesName) {
        Map<String, Object> map = new HashMap<>();
        List<String> roles = rolesMapper.queryAllRolesName();
        if (roles.contains(rolesName)) {
            map.put("resultCode", "206");
            map.put("resultMsg", "不能添加已存在的角色");
        } else {
            Integer n = rolesMapper.insertRolesByRolesName(rolesName);
            if (n != 0) {
                map.put("resultCode", "200");
                map.put("resultMsg", "添加角色成功");
            } else {
                map.put("resultCode", "204");
                map.put("resultMsg", "添加角色失败");
            }
        }
        return map;
    }

    /**
     * 删除角色
     *
     * @param rolesId
     * @return 删除角色返回信息
     */
    @SystemLog(description = "删除角色")
    public Map delectRolesByRolesId(Integer rolesId) {
        Map<String, Object> map = new HashMap<>();
        rolesMapper.delectUserRolesByRolesId(rolesId);
        rolesMapper.delectRolesPermissionByRolesId(rolesId);
        rolesMapper.delectRolesModulesByRolesId(rolesId);
        Integer n = rolesMapper.delectRolesByRolesId(rolesId);
        if (n != 0) {
            map.put("resultCode", "200");
            map.put("resultMsg", "删除角色成功");
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "删除角色失败");
        }
        return map;
    }

    /**
     * 编辑角色
     *
     * @param rolesName
     * @param rolesId
     * @return 编辑角色返回信息
     */
    @SystemLog(description = "编辑角色")
    public Map updateRolesNameByRolesName(String rolesName, Integer rolesId) {
        Map<String, Object> map = new HashMap<>();
        List<String> roles = rolesMapper.queryAllRolesName();
        if (roles.contains(rolesName)) {
            map.put("resultCode", "207");
            map.put("resultMsg", "角色已经存在，请重新输入");
        }
        Integer n = rolesMapper.updateRolesNameByRolesName(rolesName, rolesId);
        if (n != 0) {
            map.put("resultCode", "200");
            map.put("resultMsg", "修改角色成功");
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "修改角色失败");
        }
        return map;
    }

    /**
     * 查询角色未拥有权限
     *
     * @param rolesId
     * @return 查询角色未拥有权限返回信息
     */
    @SystemLog(description = "查询角色未拥有权限")
    public Map rolesPermission(Integer rolesId) {
        Map<String, Object> map = new HashMap<>();
        List<String> noPermissionValues = rolesMapper.noRolesPermission(rolesId);
        List<String> permissionValues = rolesMapper.selectPermissionByRolesId(rolesId);
        if (permissionValues != null) {
            map.put("resultCode", "200");
            map.put("resultMsg", "查询角色未拥有权限成功");
            map.put("resultnoRolesPermission", noPermissionValues);
            map.put("resultRolesPermission", permissionValues);
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "查询角色未拥有权限失败");
        }
        return map;
    }

    /**
     * 添加角色权限
     *
     * @param rolesId
     * @param permissionName
     * @return 添加角色权限返回信息
     */
    @SystemLog(description = "添加角色权限")
    public Map insertRolesPermissionByRolesId(Integer rolesId, String permissionName) {
        Map<String, Object> map = new HashMap<>();
        Integer n = rolesMapper.insertRolesPermissionByRolesId(rolesId, permissionName);
        if (n != 0) {
            map.put("resultCode", "200");
            map.put("resultMsg", "角色权限添加成功");

        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "角色权限添加失败");
        }
        return map;

    }

    /**
     * 删除角色权限
     *
     * @param rolesId
     * @param permissionName
     * @return 删除角色权限返回信息
     */
    @SystemLog(description = "删除角色权限")
    public Map removeRolesPermissions(Integer rolesId, String permissionName) {
        Map<String, Object> map = new HashMap<>();
        Integer n = rolesMapper.removeRolesPermissions(rolesId, permissionName);
        if (n != 0) {
            map.put("resultCode", "200");
            map.put("resultMsg", "角色删除权限成功");

        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "角色权限删除失败");
        }
        return map;
    }

    /**
     * 查询角色所有信息
     *
     * @param rolesId
     * @return 查询角色所有信息返回信息
     */
    @SystemLog(description = "查询角色所有信息")
    public Map queryRolesInfo(Integer rolesId) {
        Map<String, Object> map = new HashMap<>();
        List<String> permissions = rolesMapper.selectPermissionByRolesId(rolesId);
        List<String> modules = rolesMapper.selsectModulesByRolesId(rolesId);
        List<String> users = rolesMapper.selectUsersByRolesId(rolesId);
        if (permissions != null) {
            map.put("resultPermissions", permissions);
        } else {
            map.put("resultPermissions", "无权限信息");
        }
        if (modules != null) {
            map.put("resultModules", modules);
        } else {
            map.put("resultModules", "无模块信息");
        }
        if (users != null) {
            map.put("resultUsers", users);
        } else {
            map.put("resultUsers", "无用户信息");
        }
        return map;
    }

    /**
     * 根据角色ID查询所有角色未拥有模块
     *
     * @param rolesId
     * @return 根据角色ID查询所有角色未拥有模块返回模块集合
     */
    @SystemLog(description = "根据角色ID查询所有角色未拥有模块")
    public List<Modules> queryAllNoModulesByRolesId(Integer rolesId) {
        return modulesMapper.queryAllNoModulesByRolesId(rolesId);
    }

    /**
     * 根据角色ID查询所有角色拥有模块
     *
     * @param rolesId
     * @return 根据角色ID查询所有角色拥有模块返回模块集合
     */
    @SystemLog(description = "根据角色ID查询所有角色拥有模块")
    public List<Modules> queryAllModulesByRolesId(Integer rolesId) {
        return modulesMapper.queryAllModulesByRolesId(rolesId);
    }

    /**
     * 给角色添加模块
     *
     * @param rolesId
     * @param modulesId
     * @return 给角色添加模块返回信息
     */
    @SystemLog(description = "给角色添加模块")
    public Map insertModulesByRoles(Integer rolesId, Integer modulesId) {
        Map<String, Object> map = new HashMap<>();
        Integer n = modulesMapper.insertModulesByRoles(rolesId, modulesId);
        if (n != 0) {
            map.put("resultCode", "200");
            map.put("resultMsg", "设定角色模块成功");

        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "设定角色模块失败");
        }
        return map;
    }

}
