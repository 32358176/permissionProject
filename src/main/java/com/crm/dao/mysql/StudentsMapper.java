package com.crm.dao.mysql;

import com.crm.pojo.mysql.Students;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface StudentsMapper {
    static void downloadStudents(HttpServletResponse response) {
    }

    int deleteByPrimaryKey(Integer id);

    /**
     * 插入学生
     *
     * @param record
     * @return 受影响行数
     */
    int insert(Students record);

    Integer insertSelective(Students record);

    Students selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Students record);

    int updateByPrimaryKey(Students record);

    List<Students> queryAllStu();

    List<Students> queryStuBySelect(@Param("name") String name, @Param("telephone") String telephone,
                                    @Param("askerName") String askerName, @Param("date") String date,
                                    @Param("isPay") String isPay, @Param("isValid") String isValid,
                                    @Param("isVisit") String isVisit);


    Integer delectStudentByStudentId(Integer studentId);

    Integer delectStudentAskerByStudentId(Integer studentId);

    Integer updateStudentsByIds(@Param("list") Integer[] list, @Param("askerId") Integer askerId, @Param("askerName") String askerName);

    List<Students> selectStudentsByUserId(@Param("userId") Integer userId, @Param("stuname") String stuname);

    Integer delectStudentByStudentsIds(@Param("list") Integer[] list);
}