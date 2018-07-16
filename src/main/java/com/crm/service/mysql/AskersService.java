package com.crm.service.mysql;

import com.crm.dao.mysql.AskersMapper;
import com.crm.pojo.mysql.Askers;
import com.crm.pojo.mysql.Page;
import com.crm.utils.SystemLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AskersService {
    @Autowired
    private AskersMapper askersMapper;


    /**
     * 查询所有老师
     *
     * @param page
     * @param limit
     * @return Page 查询所有老师返回分页信息
     */
    @SystemLog(description = "查询所有老师")
    public Page queryAllTeacher(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Askers> askers = askersMapper.queryAllTeacher();
        return new Page(new PageInfo(askers));
    }

}
