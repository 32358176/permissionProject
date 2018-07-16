package com.crm.service.mysql;

import com.crm.dao.mysql.ModulesMapper;
import com.crm.dao.mysql.RolesMapper;
import com.crm.dao.mysql.UsersMapper;
import com.crm.interceptor.token.JSON_WEB_TOKEN;
import com.crm.interceptor.token.Token;
import com.crm.pojo.mysql.Page;
import com.crm.pojo.mysql.Users;
import com.crm.utils.IpUtils;
import com.crm.utils.PasswordEncoder;
import com.crm.utils.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class LoginService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private RolesMapper rolesMapper;

    @Autowired
    private ModulesMapper modulesMapper;
    @Autowired
    private Token token;
    @Autowired
    HttpSession session;
    @Autowired
    HttpServletRequest request;
    @Autowired
    private RedisTemplate redisTemplate;


    @SystemLog(description = "执行登陆操作")
    public Page selectUserByUserNameAndPassword(String username, String password) {
        PasswordEncoder passwordEncoder = new PasswordEncoder("hanyong", "MD5");
        password = passwordEncoder.encode(password, 5);
        Users user = usersMapper.selectUserByUserNameAndPassword(username, password);
        if (user == null) {
            usersMapper.updateUserWrongCount(username);
            return new Page(204, "用户名或密码错误");

        }
        String isLockout = selectIsLockoutByUserId(user.getId());
        if (isLockout.equals("是")) {
            return new Page(206, "用户被锁定，请联系管理员");
        }
        session.setAttribute("userId", user.getId());
        Map map = new HashMap();
        List<String> permissions = null;
        List<Integer> rolesID = null;
        List<Integer> moduleID = null;
        try {
            permissions = usersMapper.queryPermissionByUserId(user.getId());
            rolesID = rolesMapper.queryRolesByUserID(user.getId());
            moduleID = modulesMapper.queryModuleIDByrolesID(rolesID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String ip = IpUtils.getRemoteHost(request);
        usersMapper.updateUserIpAndTime(user.getId(), ip);
        map.put("userId", user.getId());
        map.put("userRoles", rolesID);
        map.put("userModules", moduleID);
        map.put("userName", user.getLoginname());
        JSON_WEB_TOKEN json_web_token = new JSON_WEB_TOKEN(user.getId(), user.getLoginname(), permissions);
        String tokenString = "";
        try {
            tokenString = token.createToken(json_web_token, 12 * 60 * 60 * 1000);
            redisTemplate.opsForValue().set(user.getLoginname(), tokenString, 4, TimeUnit.HOURS);
            map.put("TOKEN", tokenString);
            System.out.println(redisTemplate.opsForValue().get(user.getLoginname()));
        } catch (UnsupportedEncodingException e) {
            map.put("207", "编码格式异常");
        }
        return new Page(200, map);
    }

    //    @SystemLog(description = "根据用户ID查询用户是否被锁定")
    public String selectIsLockoutByUserId(Integer id) {

        return usersMapper.selectIsLockoutByUserId(id);
    }
}
