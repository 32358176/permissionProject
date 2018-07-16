package com.crm.view;

import com.crm.pojo.Page;
import com.crm.pojo.Students;
import com.crm.service.StudentsService;
import com.crm.utils.PoiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/stu", name = "学生模块")
public class StudentsController {
    @Autowired
    private StudentsService studentsService;
    @Autowired
    private PoiUtils poiUtils;

    /**
     * 查询所有学生
     * @param page
     * @param limit
     * @return Page 查询所有学生分页信息
     */
    @GetMapping(value = "/queryAllStu", name = "查询所有学生")
    public Page queryAllStu(Integer page, Integer limit) {
        return studentsService.queryAllStu(page, limit);
    }

    /**
     * 不定条件查询学生
     * @param page
     * @param limit
     * @param name
     * @param telephone
     * @param askerName
     * @param date
     * @param isPay
     * @param isValid
     * @param isVisit
     * @return Page 根据给定条件查询学生信息返回分页信息
     */
    @GetMapping(value = "/queryStuBySelect", name = "不定条件查询学生")
    public Page queryStuBySelect(Integer page, Integer limit, String name, String telephone, String askerName, String date, String isPay, String isValid, String isVisit) {

        return studentsService.queryStuBySelect(page, limit, name, telephone, askerName, date, isPay, isValid, isVisit);
    }

    /**
     * 添加学生
     * @param students
     * @return Map 添加学生返回信息
     */
    @GetMapping(value = "/insertStudentBystudent", name = "添加学生")
    public Map insertStudentBystudent(Students students) {

        return studentsService.insertStudentBystudent(students);
    }

    /**
     * 根据学生ID删除学生
     * @param studentId
     * @return Map 根据学生ID删除学生返回信息
     */
    @GetMapping(value = "/delectStudentByStudentId", name = "删除学生")
    public Map delectStudentByStudentId(Integer studentId) {
        return studentsService.delectStudentByStudentId(studentId);
    }

    /**
     * 编辑学生
     * @param students
     * @return Map 编辑学生返回信息
     */
    @GetMapping(value = "/updateStudentByStudentId", name = "编辑学生")
    public Map updateStudentByStudentId(Students students) {
        return studentsService.updateStudentByStudentId(students);
    }

    /**
     * 导出学生EXCEL
     * @param response 导出学生EXCEL
     */
    @GetMapping(value = "/downloadStudents", name = "导出学生EXCEL")
    public void downloadStudents(HttpServletResponse response) {
        studentsService.downloadStudents(response);
    }

    /**
     *上传学生EXCEL
     * @param file
     * @return
     * @throws ParseException 上传学生EXCEL
     */
    @PostMapping(value = "/uploadStudents", name = "上传学生EXCEL")
    public Page uploadStuByExcel(@RequestParam("file") MultipartFile file) throws ParseException {
        return studentsService.uploadStudents(file);
    }

    /**
     * 给学生分配老师
     * @param ids
     * @param askerId
     * @param askerName
     * @return Map 给学生分配老师返回信息
     */
    @GetMapping(value = "/updateStudentsByIds", name = "给学生分配老师")
    public Map updateStudentsByIds(Integer[] ids, Integer askerId, String askerName) {
        return studentsService.updateStudentsByIds(ids, askerId, askerName);
    }

    /**
     * 查询我的学生
     * @param page
     * @param limit
     * @param userId
     * @param stuname
     * @return Page 查询我的学生返回分页信息
     */
    @GetMapping(value = "/selectStudentsByUserId",name = "查询我的学生")
    public Page selectStudentsByUserId(Integer page,Integer limit,Integer userId,String stuname){
        return studentsService.selectStudentsByUserId(page,limit,userId,stuname);
    }

    /**
     * 批量删除学生
     * @param ids
     * @return Map 批量删除学生返回信息
     */
    @GetMapping(value = "/delectStudentByStudentsIds",name = "批量删除学生")
    public Map delectStudentByStudentsIds(Integer[] ids){
        return studentsService.delectStudentByStudentsIds(ids);
    }

}
