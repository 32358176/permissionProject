package com.crm.view;

import com.crm.pojo.Modules;
import com.crm.pojo.Page;
import com.crm.service.ModulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/modules",name = "模块系统")
public class ModulesController {

    @Autowired
    private ModulesService modulesService;

    /**
     * 根据模块ID查询模块
     * @param modulesId
     * @return List<Modules> 模块集合
     */
    @PostMapping(value = "/selectModulesByModulesId",name = "根据模块ID查询模块")
    public List<Modules> selectModulesByModulesId(@RequestParam("modules") List<Integer> modulesId){
        return modulesService.selectModulesByModulesId(modulesId);
    }

    /**
     * 查询所有模块
     * @param page
     * @param limit
     * @return Page 模块分页信息
     */
    @GetMapping(value = "/queryAllModules",name = "查询所有模块")
    public Page queryAllModules(Integer page, Integer limit){
        return modulesService.queryAllModules(page,limit);
    }

    /**
     * 添加模块查询
     * @return Map 天际模块查询返回信息
     */
    @GetMapping(value = "/queryAllModulesByAdd",name = "添加模块查询")
    public Map queryAllModulesByAdd(){
        return modulesService.queryAllModulesByAdd();
    }

    /**
     * 添加模块
     * @param modulesName
     * @param modulesPath
     * @param modulesWeight
     * @param parentId
     * @return Map 添加模块返回信息
     */
    @GetMapping(value = "/insertModulesOnAdd",name = "添加模块")
    public Map insertModulesOnAdd(String modulesName,String modulesPath,String modulesWeight,@RequestParam(required = false) Integer parentId){
        return modulesService.insertModulesOnAdd(modulesName,modulesPath,modulesWeight,parentId);
    }

    /**
     * 删除模块
     * @param modulesId
     * @return Map 删除模块返回信息
     */
    @GetMapping(value = "/delectRolesBymodulesId",name = "删除模块")
    public Map delectModulesBymodulesId(Integer modulesId){
        return modulesService.delectModulesBymodulesId(modulesId);
    }

    /**
     * 编辑模块
     * @param modulesId
     * @param modulesName
     * @param modulesWeight
     * @param modulesPath
     * @param parentId
     * @return Map 编辑模块返回信息
     */
    @GetMapping(value = "/updateModulesByModulesId",name = "编辑模块")
    public Map updateModulesByModulesId(Integer modulesId,String modulesName,String modulesWeight,String modulesPath,Integer parentId){
        return modulesService.updateModulesByModulesId(modulesId,modulesName,modulesWeight,modulesPath,parentId);
    }

    /**
     * 删除角色模块
     * @param rolesId
     * @param modulesId
     * @return Map 删除角色模块返回信息
     */
    @GetMapping(value = "/deleteModulesByRoles",name = "删除角色模块")
    public Map delectModulesByRolesId(Integer rolesId,Integer modulesId){
        return modulesService.deleteModulesByRoles(rolesId,modulesId);
    }
}
