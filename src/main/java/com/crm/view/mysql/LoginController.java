package com.crm.view;

import com.crm.interceptor.token.JSON_WEB_TOKEN;
import com.crm.interceptor.token.Token;
import com.crm.pojo.Page;
import com.crm.pojo.Permissions;
import com.crm.pojo.Users;
import com.crm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/login", name = "登陆系统")
@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return Page 用户登陆信息
     */
    @PostMapping(value = "/confirm", name = "用户登录")
    public Page login(String username, String password) {
        return loginService.selectUserByUserNameAndPassword(username, password);
    }
}
