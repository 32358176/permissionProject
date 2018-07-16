package com.crm.view.oracle;

import com.crm.pojo.oracle.Emp;
import com.crm.service.oracle.EmpSercive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/oracle")
public class testController {
    @Autowired
    private EmpSercive empSercive;

    @GetMapping("/test")
    public List<Emp> selectAll() {
//        System.out.println(empSercive.selectAll());
        return empSercive.selectAll();
    }
}
