package com.crm.dao;

import com.crm.pojo.mysql.Roles;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolesMapper {
    List<String> queryAllRolesName();

    int deleteByPrimaryKey(Integer id);

    int insert(Roles record);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);

    List<Integer> queryRolesByUserID(Integer id);

    List<String> queryRolesValueByUserID(Integer id);

    List<String> queryUserNoRoles(Integer userId);

    List<Roles> queryAllRoles();

    Integer insertRolesByRolesName(String rolesName);

    Integer delectRolesByRolesId(Integer rolesId);

    Integer updateRolesNameByRolesName(@Param("rolesName") String rolesName, @Param("rolesId") Integer rolesId);

    Integer delectUserRolesByRolesId(Integer rolesId);

    Integer delectRolesPermissionByRolesId(Integer rolesId);

    Integer delectRolesModulesByRolesId(Integer rolesId);

    List<String> noRolesPermission(Integer rolesId);

    List<String> selectPermissionByRolesId(Integer rolesId);

    Integer insertRolesPermissionByRolesId(@Param("rolesId") Integer rolesId, @Param("permissionName") String permissionName);

    Integer removeRolesPermissions(@Param("rolesId") Integer rolesId, @Param("permissionName") String permissionName);

    List<String> selsectModulesByRolesId(Integer rolesId);

    List<String> selectUsersByRolesId(Integer rolesId);
}