package com.crm.dao.mysql;

import com.crm.pojo.mysql.Askers;

import java.util.List;

public interface AskersMapper {
    int deleteByPrimaryKey(Integer askerid);

    int insert(Askers record);

    int insertSelective(Askers record);

    Askers selectByPrimaryKey(Integer askerid);

    int updateByPrimaryKeySelective(Askers record);

    int updateByPrimaryKey(Askers record);

    List<Askers> queryAllTeacher();

}