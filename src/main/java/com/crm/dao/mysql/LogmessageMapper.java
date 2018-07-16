package com.crm.dao;

import com.crm.pojo.mysql.Logmessage;


public interface LogmessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Logmessage record);

    int insertSelective(Logmessage record);

    Logmessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Logmessage record);

    int updateByPrimaryKeyWithBLOBs(Logmessage record);

    int updateByPrimaryKey(Logmessage record);
}