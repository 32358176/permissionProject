package com.crm.service.mysql;


import com.crm.dao.mysql.ModulesMapper;
import com.crm.pojo.mysql.Modules;
import com.crm.pojo.mysql.Page;
import com.crm.utils.SystemLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModulesService {
    @Autowired
    private ModulesMapper modulesMapper;


    /**
     * 根据角色ID查询模块
     *
     * @param rolesID
     * @return List<Integer> 返回模块ID集合
     */
    @SystemLog(description = "根据角色ID查询模块")
    public List<Integer> queryModuleIDByrolesID(List<Integer> rolesID) {
        List<Integer> modules = new ArrayList<>();
        if (rolesID.size() != 0) {
            modules = modulesMapper.queryModuleIDByrolesID(rolesID);
        } else {
            modules.add(0);
        }
        return modules;
    }

    /**
     * 执行了查询模块操作
     *
     * @param modulesId
     * @return 执行了查询模块操作返回模块集合
     */
    @SystemLog(description = "执行了查询模块操作")
    public List<Modules> selectModulesByModulesId(List<Integer> modulesId) {
        return modulesMapper.selectModulesByModulesId(modulesId);
    }


    /**
     * 执行了根据用户名查询用户模块操作
     *
     * @param userId
     * @return 执行了根据用户名查询用户模块操作返回模块名称集合
     */
    @SystemLog(description = "执行了根据用户名查询用户模块操作")
    public List<String> queryModulesNameByUserId(Integer userId) {
        return modulesMapper.queryModulesNameByUserId(userId);
    }

    /**
     * 查询所有模块
     *
     * @param page
     * @param limit
     * @return 查询所有模块返回分页信息
     */
    @SystemLog(description = "查询所有模块")
    public Page queryAllModules(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Modules> modules = modulesMapper.queryAllModules();
        return new Page(new PageInfo(modules));
    }

    /**
     * 添加模块中查询所有模块操作
     *
     * @return 添加模块中查询所有模块操作返回信息
     */
    @SystemLog(description = "添加模块中查询所有模块操作")
    public Map queryAllModulesByAdd() {
        Map<String, Object> map = new HashMap<>();
        List<Modules> modules = modulesMapper.queryAllModules();
        if (modules != null) {
            map.put("resultCode", "200");
            map.put("resultMsg", "查询成功");
            map.put("resultModules", modules);
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "查询失败");
        }
        return map;
    }

    /**
     * 添加新模块
     *
     * @param modulesName
     * @param modulesPath
     * @param modulesWeight
     * @param parentId
     * @return 添加新模块返回信息
     */
    @SystemLog(description = "添加新模块")
    public Map insertModulesOnAdd(String modulesName, String modulesPath, String modulesWeight, Integer parentId) {
        Map<String, Object> map = new HashMap<>();
        List<String> modules = modulesMapper.queryAllModulesName();
        if (modules.contains(modulesName)) {
            map.put("resultCode", "206");
            map.put("resultCode", "模块已存在不能重复添加");
        }
        Integer n = modulesMapper.insertModulesOnAdd(modulesName, modulesPath, modulesWeight, parentId);
        if (n != 0) {
            map.put("resultCode", "200");
            map.put("resultMsg", "添加模块成功");
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "添加模块失败");
        }

        return map;
    }

    /**
     * 根据模块ID删除模块
     *
     * @param modulesId
     * @return 根据模块ID删除模块返回信息
     */
    @SystemLog(description = "根据模块ID删除模块")
    public Map delectModulesBymodulesId(Integer modulesId) {
        Map<String, Object> map = new HashMap<>();
        List<Modules> list = modulesMapper.selectModulesByParentsId(modulesId);
        Integer n = 0;
        if (list.size() == 0) {
            modulesMapper.deleteRolesModulesByModulesId(modulesId);
            n = modulesMapper.delectModulesBymodulesId(modulesId);
        } else {
            modulesMapper.deleteRolesModulesByModulesId(modulesId);
            n = modulesMapper.delectModulesBymodulesId(modulesId);
            for (Modules module : list) {
                this.delectModulesBymodulesId(module.getId());
            }
        }
        if (n != 0) {
            map.put("resultCode", "200");
            map.put("resultMsg", "删除模块成功");
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "删除模块失败");
        }
        return map;
    }

    /**
     * 根据模块ID编辑模块
     *
     * @param modulesId
     * @param modulesName
     * @param modulesWeight
     * @param modulesPath
     * @param parentId
     * @return 根据模块ID编辑模块返回信息
     */
    @SystemLog(description = "根据模块ID编辑模块")
    public Map updateModulesByModulesId(Integer modulesId, String modulesName, String modulesWeight, String modulesPath, Integer parentId) {
        Map<String, Object> map = new HashMap<>();
        List<String> allModulesName = modulesMapper.queryAllModulesName();
        if (allModulesName.contains(modulesName)) {
            map.put("resultCode", "207");
            map.put("resultMsg", "模块名称已经存在");
        }
        Integer n = modulesMapper.updateModulesByModulesId(modulesId, modulesName, modulesWeight, modulesPath, parentId);
        if (n != 0) {
            map.put("resultCode", "200");
            map.put("resultMsg", "编辑模块成功");
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "编辑模块失败");
        }
        return map;
    }

    /**
     * 根据角色ID删除模块
     *
     * @param rolesId
     * @param modulesId
     * @return 根据角色ID删除模块返回信息
     */
    @SystemLog(description = "根据角色ID删除模块")
    public Map deleteModulesByRoles(Integer rolesId, Integer modulesId) {
        Map<String, Object> map = new HashMap<>();
        Integer n = modulesMapper.deleteModulesByRoles(rolesId, modulesId);
        if (n != 0) {
            map.put("resultCode", "200");
            map.put("resultMsg", "删除角色模块成功");
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "删除角色模块失败");
        }
        return map;
    }
}
