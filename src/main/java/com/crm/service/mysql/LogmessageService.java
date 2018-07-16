package com.crm.service;

import com.crm.dao.LogmessageMapper;
import com.crm.pojo.Logmessage;
import com.crm.utils.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LogmessageService {

    @Autowired
    private LogmessageMapper logmessageMapper;


    public Integer insertLog(Logmessage logmessage){
       return logmessageMapper.insertSelective(logmessage);
    }
}
