package com.crm.service.mysql;

import com.crm.dao.mysql.StudentsMapper;
import com.crm.pojo.mysql.Page;
import com.crm.pojo.mysql.Students;
import com.crm.utils.PoiUtils;
import com.crm.utils.SystemLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentsService {
    @Autowired
    private StudentsMapper studentsMapper;
    @Autowired
    private PoiUtils poiUtils;

    /**
     * 查询所有学生
     *
     * @param page
     * @param limit
     * @return 查询所有学生返回分页信息
     */
    @SystemLog(description = "查询所有学生")
    public Page queryAllStu(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Students> students = studentsMapper.queryAllStu();
        return new Page(new PageInfo(students));
    }

    /**
     * 根据选择查询学生
     *
     * @param page
     * @param limit
     * @param name
     * @param telephone
     * @param askerName
     * @param date
     * @param isPay
     * @param isValid
     * @param isVisit
     * @return 根据选择查询学生返回分页信息
     */
    @SystemLog(description = "根据选择查询学生")
    public Page queryStuBySelect(Integer page, Integer limit, String name, String telephone, String askerName, String date, String isPay, String isValid, String isVisit) {
        PageHelper.startPage(page, limit);
        List<Students> students = studentsMapper.queryStuBySelect(name, telephone, askerName, date, isPay, isValid, isVisit);
        return new Page(new PageInfo(students));
    }

    /**
     * 添加新学生
     *
     * @param students
     * @return 添加新学生返回信息
     */
    @SystemLog(description = "添加新学生", isWrite = false)
    public Map insertStudentBystudent(Students students) {
        Map<String, Object> map = new HashMap<>();
        Integer n = studentsMapper.insertSelective(students);
        if (n != 0) {
            map.put("resultCode", "200");
            map.put("resultMsg", "添加学生成功");
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "添加学生失败");
        }
        return map;
    }

    /**
     * 删除学生
     *
     * @param studentId
     * @return 删除学生返回信息
     */
    @SystemLog(description = "删除学生")
    public Map delectStudentByStudentId(Integer studentId) {
        Map<String, Object> map = new HashMap<>();
        studentsMapper.delectStudentAskerByStudentId(studentId);
        Integer n = studentsMapper.delectStudentByStudentId(studentId);
        if (n != 0) {
            map.put("resultCode", "200");
            map.put("resultMsg", "删除学生成功!");
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "删除学生失败!");
        }
        return map;
    }

    /**
     * 编辑学生
     *
     * @param students
     * @return 编辑学生返回信息
     */
    @SystemLog(description = "编辑学生", isWrite = false)
    public Map updateStudentByStudentId(Students students) {
        Map<String, Object> map = new HashMap<>();
        Integer n = studentsMapper.updateByPrimaryKeySelective(students);
        if (n != 0) {
            map.put("resultCode", "200");
            map.put("resultMsg", "编辑学生成功");
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "编辑学生失败");
        }
        return map;
    }

    /**
     * 导出学生表
     *
     * @param response
     */
    @SystemLog(description = "导出学生表", isWrite = false)
    public void downloadStudents(HttpServletResponse response) {
        //定义表头
        String[] tableHeader = {"ID", "姓名", "性别", "联系电话", "咨询师", "是否缴费", "是否回访", "QQ", "创建时间", "上门时间", "首次回访时间", "缴费时间", "进班时间"};
        //创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置单元格样式
        HSSFCellStyle style = workbook.createCellStyle();
        //居中
        style.setAlignment(HorizontalAlignment.CENTER);
        // 创建第一个工作表
        HSSFSheet sheet = workbook.createSheet("学生信息表");
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
            sheet.setColumnWidth(i, 55 * 100);
        }


        // 获取要导出的所有学生
        List<Students> list = studentsMapper.queryAllStu();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < list.size(); i++) {
            // 从第二行开始
            HSSFRow hssfRow = sheet.createRow(i + 1);
            // 每一行对应的学生
            Students students = list.get(i);
            // 给每个单元格赋值
            try {
                hssfRow.createCell(0).setCellValue(students.getId());
                hssfRow.createCell(1).setCellValue(students.getName());
                hssfRow.createCell(2).setCellValue(students.getSex());
                hssfRow.createCell(3).setCellValue(students.getPhone());
                hssfRow.createCell(4).setCellValue(students.getAskerid());
                hssfRow.createCell(5).setCellValue(students.getIspay());
                hssfRow.createCell(6).setCellValue(students.getIsreturnvist());
                hssfRow.createCell(7).setCellValue(students.getQq());
                hssfRow.createCell(8).setCellValue(simpleDateFormat.format(students.getCreatetime()));
                hssfRow.createCell(9).setCellValue(simpleDateFormat.format(students.getHometime()));
                hssfRow.createCell(10).setCellValue(simpleDateFormat.format(students.getFirstvisittime()));
                hssfRow.createCell(11).setCellValue(simpleDateFormat.format(students.getPaytime()));
                hssfRow.createCell(12).setCellValue(simpleDateFormat.format(students.getInclasstime()));
            } catch (Exception e) {
                hssfRow.createCell(7).setCellValue("");
            }

        }

        // 设置excel文件名称
        String fileName = "学生信息.xls";
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

    /**
     * 上传学生表
     *
     * @param file
     * @return 上传学生表返回信息
     * @throws ParseException
     */
    @SystemLog(description = "上传学生表", isWrite = false)
    public Page uploadStudents(MultipartFile file) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<String[]> strings = poiUtils.getWorkbookValue(file);
        List<Students> student = new ArrayList<>();
        for (String[] str : strings) {
            Students students = new Students();
            students.setName(str[1]);
            students.setSex(str[2]);
            students.setPhone(str[3]);
            students.setAskerid(str[4]);
            students.setIspay(str[5]);
            students.setIsreturnvist(str[6]);
            students.setQq(str[7]);
            students.setCreatetime(simpleDateFormat.parse(str[8]));
            students.setHometime(simpleDateFormat.parse(str[9]));
            students.setFirstvisittime(simpleDateFormat.parse(str[10]));
            students.setPaytime(simpleDateFormat.parse(str[11]));
            students.setInclasstime(simpleDateFormat.parse(str[12]));
            student.add(students);
        }
        return batchStudents(student);
    }

    public Page batchStudents(List<Students> students) {
        boolean flag = true;
        for (Students s : students) {
            Integer count = studentsMapper.insertSelective(s);
            if (count < 1) {
                flag = false;
            }
        }
        Page page = flag ? new Page(200) : new Page(204);
        return page;

    }


    /**
     * 分配老师
     *
     * @param ids
     * @param askerId
     * @param askerName
     * @return 分配老师返回信息
     */
    @SystemLog(description = "分配老师")
    public Map updateStudentsByIds(Integer[] ids, Integer askerId, String askerName) {
        Map<String, Object> map = new HashMap<>();
        Integer n = studentsMapper.updateStudentsByIds(ids, askerId, askerName);
        if (n != 0) {
            map.put("resultCode", "200");
            map.put("resultMsg", "分配老师成功");
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "分配老师失败");
        }
        return map;
    }

    /**
     * 查询我的学生
     *
     * @param page
     * @param limit
     * @param userId
     * @param stuname
     * @return 查询我的学生返回信息
     */
    @SystemLog(description = "查询我的学生")
    public Page selectStudentsByUserId(Integer page, Integer limit, Integer userId, String stuname) {
        PageHelper.startPage(page, limit);
        List<Students> students = studentsMapper.selectStudentsByUserId(userId, stuname);
        return new Page(new PageInfo(students));
    }

    /**
     * 批量删除学生
     *
     * @param ids
     * @return 批量删除学生返回信息
     */
    @SystemLog(description = "批量删除学生")
    public Map delectStudentByStudentsIds(Integer[] ids) {
        Map<String, Object> map = new HashMap<>();
        if (ids.length == 0) {
            map.put("resultCode", "206");
            map.put("resultMsg", "请先选择");
        }
        Integer n = studentsMapper.delectStudentByStudentsIds(ids);
        if (n != 0) {
            map.put("resultCode", "200");
            map.put("resultMsg", "删除成功");
        } else {
            map.put("resultCode", "204");
            map.put("resultMsg", "删除失败");
        }
        return map;
    }
}
