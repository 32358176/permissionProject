package com.crm.dao;

import com.crm.pojo.mysql.Permissions;

import java.util.List;

public interface PermissionsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permissions record);

    int insertSelective(Permissions record);

    Permissions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permissions record);

    int updateByPrimaryKey(Permissions record);

    List<String> queryAll();

    int batchInsert(List<Permissions> permissiontbs);

    List<Permissions> queryAllpermissions();
}