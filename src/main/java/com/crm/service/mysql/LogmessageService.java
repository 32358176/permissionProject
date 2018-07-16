package com.crm.service.mysql;

import com.crm.dao.mysql.LogmessageMapper;
import com.crm.pojo.mysql.Logmessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LogmessageService {

    @Autowired
    private LogmessageMapper logmessageMapper;


    public Integer insertLog(Logmessage logmessage) {
        return logmessageMapper.insertSelective(logmessage);
    }
}
