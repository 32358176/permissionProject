package com.crm.dao.mysql;

import com.crm.pojo.mysql.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Users selectUserByUserNameAndPassword(@Param("username") String username, @Param("password") String password);

    List<String> queryPermissionByUserId(Integer id);

    String selectIsLockoutByUserId(Integer id);

    List<Users> queryAllUsers();

    List<Users> queryUserByTerm(@Param("username") String username, @Param("isLock") String isLock);

    List<Users> queryUserByUserName(String username);

    List<Users> queryUserByisLock(String isLock);

    List<String> selectRolesByUserId(Integer userId);

    String selectUsernameByUserId(Integer userId);

    Integer delectUserByUserId(Integer userId);

    Integer deleteUserRoleByUserId(Integer userId);

    Users selectUserByUsername(String username);

    Integer insertUser(@Param("username") String username, @Param("password") String password, @Param("email") String email, @Param("telephone") String telephone);

    Integer updateUserByUser(@Param("userid") Integer userid, @Param("username") String username, @Param("password") String password, @Param("email") String email, @Param("telephone") String telephone);

    Integer resetUserPassword(@Param("userId") Integer userId, @Param("password") String password);

    Integer lockUserByUserId(@Param("userId") Integer userId, @Param("isLockout") String isLockout);

    Users selectUserByUserId(Integer userId);

    Integer inertUserRolesByUserId(@Param("userId") Integer userId, @Param("roleName") String roleName);

    Integer removeUserRoleByUserId(@Param("userId") Integer userId, @Param("roleName") String roleName);

    Integer updateUserWrongCount(String username);

    Integer clearWrongTime(Integer userId);

    Integer updateUserIpAndTime(@Param("userId") Integer userId, @Param("Ip") String Ip);
}