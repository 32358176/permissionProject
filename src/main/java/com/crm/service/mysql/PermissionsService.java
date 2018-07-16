package com.crm.service.mysql;

import com.crm.dao.mysql.PermissionsMapper;
import com.crm.dao.mysql.UsersMapper;
import com.crm.pojo.mysql.Page;
import com.crm.pojo.mysql.Permissions;
import com.crm.utils.SystemLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionsService {

    @Autowired
    private PermissionsMapper permissionsMapper;

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 查询所有权限名称
     *
     * @return 查询所有权限名称返回权限名称集合
     */
    @SystemLog(description = "查询所有权限名称")
    public List<String> queryAll() {
        return permissionsMapper.queryAll();
    }


    /**
     * 插入权限操作
     *
     * @param permissiontbs
     * @return 插入权限操作返回影像行数
     */
    @SystemLog(description = "插入权限操作", isWrite = false)
    public int insertSystemPermission(List<Permissions> permissiontbs) {
        return permissionsMapper.batchInsert(permissiontbs);

    }

    /**
     * 根据用户ID查询用户权限操作
     *
     * @param id
     * @return 根据用户ID查询用户权限操作返回权限名称集合
     */
    @SystemLog(description = "根据用户ID查询用户权限操作")
    public List<String> queryPermissionByUserId(Integer id) {
        return usersMapper.queryPermissionByUserId(id);
    }

    /**
     * 查询所有权限
     *
     * @param page
     * @param limit
     * @return 查询所有权限返回分页信息
     */
    @SystemLog(description = "查询所有权限")
    public Page queryAllpermissions(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Permissions> permissions = permissionsMapper.queryAllpermissions();
        return new Page(new PageInfo(permissions));
    }
}
