package com.crm.dao.mysql;

import com.crm.pojo.mysql.Modules;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModulesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Modules record);

    int insertSelective(Modules record);

    Modules selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Modules record);

    int updateByPrimaryKey(Modules record);

    List<Integer> queryModuleIDByrolesID(List<Integer> rolesID);

    List<Modules> selectModulesByModulesId(List<Integer> list);

    List<String> queryModulesNameByUserId(Integer userId);

    List<Modules> queryAllModules();

    Integer insertModulesOnAdd(@Param("modulesName") String modulesName, @Param("modulesPath") String modulesPath, @Param("modulesWeight") String modulesWeight, @Param("parentId") Integer parentId);

    List<String> queryAllModulesName();

    Integer delectModulesBymodulesId(Integer modulesId);

    Integer deleteRolesModulesByModulesId(Integer modulesId);

    List<Modules> selectModulesByParentsId(Integer modulesId);

    Integer updateModulesByModulesId(@Param("modulesId") Integer modulesId, @Param("modulesName") String modulesName, @Param("modulesWeight") String modulesWeight, @Param("modulesPath") String modulesPath, @Param("parentId") Integer parentId);

    List<Modules> queryAllNoModulesByRolesId(Integer rolesId);

    List<Modules> queryAllModulesByRolesId(Integer rolesId);

    Integer insertModulesByRoles(@Param("rolesId") Integer rolesId, @Param("modulesId") Integer modulesId);

    Integer deleteModulesByRoles(@Param("rolesId") Integer rolesId, @Param("modulesId") Integer modulesId);
}