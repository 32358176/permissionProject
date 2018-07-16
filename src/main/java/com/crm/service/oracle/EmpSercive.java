package com.crm.service.oracle;

import com.crm.dao.oracle.EmpMapper;
import com.crm.pojo.oracle.Emp;
import com.crm.utils.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpSercive {

    @Autowired
    private EmpMapper empMapper;


    public List<Emp> selectAll() {
        return empMapper.selectAll();
    }
}
