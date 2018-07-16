package com.crm.service.mysql;

import com.crm.dao.mysql.ModulesMapper;
import com.crm.dao.mysql.RolesMapper;
import com.crm.dao.mysql.UsersMapper;
import com.crm.pojo.mysql.Page;
import com.crm.pojo.mysql.Roles;
import com.crm.pojo.mysql.Users;
import com.crm.utils.PasswordEncoder;
import com.crm.utils.SystemLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private ModulesMapper modulesMapper;

    @Autowired
    private RolesMapper rolesMapper;

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return 根据用户名查询用户信息返回信息
     */
    @SystemLog(description = "根据用户名查询用户信息")
    public Map selectUserByUsername(String username) {
        Map<String, Object> map = new HashMap<>();
        Users user = usersMapper.selectUserByUsername(username);
        if (user != null) {
            map.put("resultCode", "200");
            map.put("resultMsg", "查询成功");
            map.put("resultUser", user);
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "查询失败");
        }
        return map;
    }

    /**
     * 根据用户是否被锁定查询用户信息
     *
     * @param isLockout
     * @return 根据用户是否被锁定查询用户信息返回分页信息
     */
    @SystemLog(description = "根据用户是否被锁定查询用户信息")
    public Page selectUserByIsLockout(Integer isLockout) {
        return null;
    }

    /**
     * 根据用户角色查询用户信息
     *
     * @param roles
     * @return 根据用户角色查询用户信息返回分页信息
     */
    @SystemLog(description = "根据用户角色查询用户信息", isWrite = false)
    public Page selectUserByRoles(List<Roles> roles) {

        return null;
    }


    /**
     * 创建新用户
     *
     * @param username
     * @param password
     * @param email
     * @param telephone
     * @return 创建新用户返回信息
     */
    @SystemLog(description = "创建新用户", isWrite = false)
    public Map insertNewUser(String username, String password, String email, String telephone) {
        Map<String, Object> map = new HashMap<>();
        PasswordEncoder passwordEncoder = new PasswordEncoder("hanyong", "MD5");
        password = passwordEncoder.encode(password, 5);
        Integer n = usersMapper.insertUser(username, password, email, telephone);
        if (n != 0) {
            map.put("resultCode", "200");
            map.put("resultMsg", "添加成功");
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "添加失败");
        }
        return map;
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return 删除用户返回影响行数
     */
    @SystemLog(description = "删除用户")
    public Integer delectUserByUserId(Integer userId) {
        Integer n = 0;
        List<String> roles = usersMapper.selectRolesByUserId(userId);
        if (roles.size() > 0) {
            usersMapper.deleteUserRoleByUserId(userId);
            n = usersMapper.delectUserByUserId(userId);
        } else {
            n = usersMapper.delectUserByUserId(userId);
        }
        return n;
    }


    /**
     * 重置用户密码
     *
     * @param userId
     * @return 重置用户密码返回信息
     */
    @SystemLog(description = "重置用户密码")
    public Map resetUserPassword(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        PasswordEncoder passwordEncoder = new PasswordEncoder("hanyong", "MD5");
        String password = passwordEncoder.encode("123", 5);
        Integer n = usersMapper.resetUserPassword(userId, password);
        if (n != 0) {
            map.put("resultCode", "200");
            map.put("resultMsg", "重置密码成功");
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "重置密码失败");
        }
        return map;
    }

    /**
     * 锁定用户
     *
     * @param userId
     * @param isLockout
     * @return 锁定用户返回信息
     */
    @SystemLog(description = "锁定用户")
    public Map lockUserByUserId(Integer userId, String isLockout) {
        Map<String, Object> map = new HashMap<>();
        usersMapper.clearWrongTime(userId);
        Integer n = usersMapper.lockUserByUserId(userId, isLockout);
        if (n != 0) {
            map.put("resultCode", "200");
            map.put("resultMsg", "改变锁定状态成功");
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "改变锁定状态失败");
        }
        return map;
    }

    /**
     * 查询所有用户
     *
     * @param page
     * @param limit
     * @return 查询所有用户返回分页信息
     */
    @SystemLog(description = "查询所有用户")
    public Page queryAllUsers(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Users> users = usersMapper.queryAllUsers();
        return new Page(new PageInfo(users));
    }

    /**
     * 不定条件查询
     *
     * @param username
     * @param isLock
     * @param page
     * @param limit
     * @return 不定条件查询返回分页信息
     */
    @SystemLog(description = "不定条件查询")
    public Page queryUserByTerm(String username, String isLock, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Users> users = usersMapper.queryUserByTerm(username, isLock);
        return new Page(new PageInfo(users));

    }

    /**
     * 执行了根据用户ID查询用户角色操作
     *
     * @param userId
     * @return 执行了根据用户ID查询用户角色操作返回角色集合
     */
    @SystemLog(description = "执行了根据用户ID查询用户角色操作")
    public List<String> selectRolesByUserId(Integer userId) {
        return usersMapper.selectRolesByUserId(userId);
    }

    /**
     * 执行了根据用户ID查询用户名操作
     *
     * @param userId
     * @return 执行了根据用户ID查询用户名操作返回用户名
     */
    @SystemLog(description = "执行了根据用户ID查询用户名操作")
    public String selectUserIdByUserId(Integer userId) {
        return usersMapper.selectUsernameByUserId(userId);
    }

    /**
     * 编辑用户信息
     *
     * @param userid
     * @param username
     * @param password
     * @param email
     * @param telephone
     * @return 编辑用户信息返回信息
     */
    @SystemLog(description = "编辑用户信息")
    public Map updateUserByUser(Integer userid, String username, String password, String email, String telephone) {
        Map<String, Object> map = new HashMap<>();
        Integer n = usersMapper.updateUserByUser(userid, username, password, email, telephone);
        if (n != 0) {
            map.put("resultCode", "200");
            map.put("resultMsg", "修改成功");
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "修改失败");
        }
        return map;
    }

    /**
     * 执行根据用户ID查询用户操作
     *
     * @param userId
     * @return 执行根据用户ID查询用户操作返回信息
     */
    @SystemLog(description = "执行根据用户ID查询用户操作")
    public Map selectUserByUserId(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        Users user = usersMapper.selectUserByUserId(userId);
        if (user != null) {
            map.put("resultCode", "200");
            map.put("resultMsg", "查询用户成功");
            map.put("resultUser", user);
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "查询用户失败");
        }
        return map;


    }

    /**
     * 执行查询用户所有信息操作
     *
     * @param userId
     * @return 执行查询用户所有信息操作返回信息
     */
    @SystemLog(description = "执行查询用户所有信息操作")
    public Map queryUserAllInfo(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        Users user = usersMapper.selectUserByUserId(userId);
        List<String> roles = usersMapper.selectRolesByUserId(userId);
        List<String> permissions = usersMapper.queryPermissionByUserId(userId);
        List<String> modules = modulesMapper.queryModulesNameByUserId(userId);
        System.out.println(modules.toString());
        if (user != null) {
            map.put("resultCode", "200");
            map.put("resultMsg", "查询用户信息成功");
            map.put("resultUser", user);
            map.put("resultRoles", roles);
            map.put("resultPermissions", permissions);
            map.put("resultModules", modules);
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "查询用户信息失败");
        }
        return map;
    }

    /**
     * 根据用户ID查询所有角色
     *
     * @param userId
     * @return 根据用户ID查询所有角色返回信息
     */
    @SystemLog(description = "根据用户ID查询所有角色")
    public Map queryRolesInfoByUserId(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        List<String> roles = usersMapper.selectRolesByUserId(userId);
        List<String> allRoles = rolesMapper.queryUserNoRoles(userId);
        if (roles != null) {
            map.put("resultCode", "200");
            map.put("resultMsg", "用户角色信息成功");
            map.put("resultRoles", roles);
            map.put("resultAllRoles", allRoles);
        } else {
            map.put("resultCode", "204");
            map.put("resultData", "无角色");
            map.put("resultMsg", "用户未被分配角色");
        }
        return map;
    }

    /**
     * 根据用户ID设置用户角色
     *
     * @param userId
     * @param roleName
     * @return 根据用户ID设置用户角色返回信息
     */
    @SystemLog(description = "根据用户ID设置用户角色")
    public Map inertUserRolesByUserId(Integer userId, String roleName) {
        Map<String, Object> map = new HashMap<>();
        List<String> roles = usersMapper.selectRolesByUserId(userId);
        if (roles.contains(roleName)) {
            map.put("resultCode", "205");
            map.put("resultMsg", "用户角色已经存在，不能重复添加");
        } else {
            Integer n = usersMapper.inertUserRolesByUserId(userId, roleName);
            if (n != 0) {
                map.put("resultCode", "200");
                map.put("resultMsg", "添加角色成功");

            } else {
                map.put("resultCode", "204");
                map.put("resultData", "添加角色失败");
            }
        }
        return map;
    }

    /**
     * 根据用户ID删除用户角色
     *
     * @param userId
     * @param roleName
     * @return 根据用户ID删除用户角色返回信息
     */
    @SystemLog(description = "根据用户ID删除用户角色")
    public Map removeUserRoleByUserId(Integer userId, String roleName) {
        Map<String, Object> map = new HashMap<>();
        Integer n = usersMapper.removeUserRoleByUserId(userId, roleName);
        if (n != 0) {
            map.put("resultCode", "200");
            map.put("resultMsg", "移除用户角色成功");
        } else {
            map.put("resultCode", "204");
            map.put("resultData", "移除用户角色失败");
        }
        return map;
    }

    /**
     * 导出用户信息
     *
     * @param response
     */
    @SystemLog(description = "导出用户信息", isWrite = false)
    public void downloadUserInfo(HttpServletResponse response) {
        //定义表头
        String[] tableHeader = {"用户名", "锁定状态", "上一次登陆时间", "密码错误次数", "锁定时间", "安全邮箱", "安全手机", "上一次登陆IP", "创建时间"};
        //创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置单元格样式
        HSSFCellStyle style = workbook.createCellStyle();
        //居中
        style.setAlignment(HorizontalAlignment.CENTER);
        // 创建第一个工作表
        HSSFSheet sheet = workbook.createSheet("学生表");
        // 创建第一行
        HSSFRow row = sheet.createRow(0);

        for (int i = 0; i < tableHeader.length; i++) {
            // 创建单元格
            HSSFCell cell = row.createCell(i);
            // 给单元格设置内容
            cell.setCellValue(tableHeader[i]);
            //将单元格居中
            cell.setCellStyle(style);
            //自动添加列
            sheet.autoSizeColumn(i);
            //列宽
            sheet.setColumnWidth(i, 50 * 100);
        }


        // 获取要导出的所有学生
        List<Users> list = usersMapper.queryAllUsers();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < list.size(); i++) {
            // 从第二行开始
            HSSFRow hssfRow = sheet.createRow(i + 1);
            // 每一行对应的学生
            Users users = list.get(i);
            // 给每个单元格赋值
            hssfRow.createCell(0).setCellValue(users.getLoginname());
            hssfRow.createCell(1).setCellValue(users.getIslockout());
            hssfRow.createCell(2).setCellValue(simpleDateFormat.format(users.getLastlogintime()));
            hssfRow.createCell(3).setCellValue(users.getPsdwrongtime());
            hssfRow.createCell(4).setCellValue(simpleDateFormat.format(users.getLocktime()));
            hssfRow.createCell(5).setCellValue(users.getProtectemail());
            hssfRow.createCell(6).setCellValue(users.getProtectmtel());
            hssfRow.createCell(7).setCellValue(users.getLastloginip());
            hssfRow.createCell(8).setCellValue(simpleDateFormat.format(users.getCreatetime()));

        }

        // 设置excel文件名称
        String fileName = "用户信息.xls";
        //避免下载文件名出现乱码
        OutputStream outputStream = null;
        try {
            fileName = URLEncoder.encode(fileName, "UTF8");
            //开始输出工作簿
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 重置response设置
        response.reset();
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.setContentType("application/vnd.ms-excel");
        // 发送工作簿
        try {
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
