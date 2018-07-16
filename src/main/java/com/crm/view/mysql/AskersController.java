package com.crm.view.mysql;

import com.crm.pojo.mysql.Page;
import com.crm.service.mysql.AskersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping(value = "/asker", name = "老师模块")

public class AskersController {

    @Autowired
    private AskersService askersService;

    /**
     * 查询所有老师
     *
     * @param page
     * @param limit
     * @return Page 查询所有老师集合
     */
    @GetMapping(value = "/queryAllTeacher", name = "查询所有老师")
    public Page queryAllTeacher(Integer page, Integer limit) {
        return askersService.queryAllTeacher(page, limit);
    }

}
