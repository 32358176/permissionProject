package com.crm.view;

import com.crm.pojo.Page;
import com.crm.pojo.Roles;
import com.crm.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/user",name = "用户模块")
public class UserController {

    @Autowired
    private UsersService usersService;


    /**
     * 根据用户名查询用户信息
     * @param username
     * @return Map 根据用户名查询用户信息返回信息
     */
    @GetMapping(value = "/selectUserByUsername",name = "根据用户名查询用户信息")
    public Map selectUserByUsername(String username){
              return usersService.selectUserByUsername(username);

    }

    /**
     * 不定条件查询
     * @param username
     * @param isLock
     * @param page
     * @param limit
     * @return Page 根据给定条件查询用户信息返回分页信息
     */
    @GetMapping(value = "/queryUserByTerm",name = "不定条件查询")
    public Page queryUserByTerm(String username,String isLock , Integer page, Integer limit){
       return usersService.queryUserByTerm(username,isLock,page,limit);
    }

    /**
     *查询所有用户
     * @param page
     * @param limit
     * @return Page 查询所有用户返回分页信息
     */
    @GetMapping(value = "/queryAllUsers",name = "查询所有用户")
    public Page queryAllUsers(Integer page,Integer limit){
       return usersService.queryAllUsers(page,limit);
    }

    /**
     *根据用户ID查询用户数据
     * @param userId
     * @return Map 根据用户ID查询用户数据返回信息
     */
    @GetMapping(value = "/selectUserByUserId",name = "根据用户ID查询用户数据")
    public Map selectUserByUserId(Integer userId){
            return usersService.selectUserByUserId(userId);
    }

    /**
     *根据用户是否被锁定查询用户信息
     * @param isLockout
     * @return Page 根据用户是否被锁定查询用户信息返回分页信息
     */
    @GetMapping(value = "/selectUserByIsLockout",name = "根据用户是否被锁定查询用户信息")
    public Page selectUserByIsLockout(Integer isLockout){
        return usersService.selectUserByIsLockout(isLockout);
    }


    /**
     *根据用户角色查询用户信息
     * @param roles
     * @return Page 根据用户角色查询用户信息返回分页信息
     */
    @GetMapping(value = "/selectUserByRoles",name = "根据用户角色查询用户信息")
    public Page selectUserByRoles(List<Roles> roles){
        return usersService.selectUserByRoles(roles);
    }

    /**
     *创建新用户
     * @param username
     * @param password
     * @param email
     * @param telephone
     * @return Map 创建新用户返回信息
     */
    @GetMapping(value = "/insertNewUser",name = "创建新用户")
    public Map insertNewUser(String username,String password,String email,String telephone){
        return usersService.insertNewUser(username,password,email,telephone);
    }


    /**
     * 删除用户
     * @param userId
     * @return Map 删除用户返回信息
     */
    @GetMapping(value = "/delectUserByUserId",name = "删除用户")
    public Map<String, Object> delectUserByUserId(Integer userId){

        Map<String,Object> map = new HashMap<>();
        int n =usersService.delectUserByUserId(userId);
        if(n != 0){
            map.put("resultCode","200");
            map.put("resultMsg","成功删除了" + n + "条数据");
        }else{
            map.put("resultCode","204");
            map.put("resultMsg","删除失败");
        }
        return map;
    }


    @GetMapping(value = "/updateUserByUser",name = "编辑用户信息")
    public Map updateUserByUser(Integer userid,String username,String password,String email,String telephone){
        return usersService.updateUserByUser(userid,username,password,email,telephone);
    }

    @GetMapping(value = "/resetUserPassword",name = "重置用户密码")
    public Map resetUserPassword(Integer userId){
        return usersService.resetUserPassword(userId);
    }

    @GetMapping(value = "/lockUserByUserId",name = "锁定用户")
    public Map lockUserByUserId(Integer userId,String isLockout){
        return usersService.lockUserByUserId(userId,isLockout);
    }

    @GetMapping(value = "/queryRolesInfoByUserId",name = "根据用户ID查找用户角色信息")
    public Map queryRolesInfoByUserId(Integer userId){
        return usersService.queryRolesInfoByUserId(userId);
    }

    @GetMapping(value = "/queryUserAllInfo",name = "查询用户所有信息")
    public Map queryUserAllInfo(Integer userId){
        return usersService.queryUserAllInfo(userId);
    }

    @GetMapping(value = "/addUserRolesByUserId",name = "添加用户角色")
    public Map inertUserRolesByUserId(Integer userId,String roleName ){
        return usersService.inertUserRolesByUserId(userId,roleName);
    }

    @GetMapping(value = "/removeUserRoleByUserId",name = "给用户设定角色")
    public Map removeUserRoleByUserId(Integer userId, String roleName){
        return usersService.removeUserRoleByUserId(userId,roleName);
    }

    @GetMapping(value = "/downloadUserInfo",name = "导出EXCEL")
    public void downloadUserInfo(HttpServletResponse response){
       usersService.downloadUserInfo(response);
    }




}
