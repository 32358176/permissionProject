package com.crm.view.mysql;

import com.crm.pojo.mysql.Page;
import com.crm.service.mysql.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/login", name = "登陆系统")
@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 用户登陆
     *
     * @param username
     * @param password
     * @return Page 用户登陆信息
     */
    @PostMapping(value = "/confirm", name = "用户登录")
    public Page login(String username, String password) {
        return loginService.selectUserByUserNameAndPassword(username, password);
    }
}
