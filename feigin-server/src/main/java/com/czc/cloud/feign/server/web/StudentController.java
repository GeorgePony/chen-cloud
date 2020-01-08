package com.czc.cloud.feign.server.web;

import com.alibaba.fastjson.JSONObject;
import com.czc.cloud.feign.server.bean.Student;
import com.czc.cloud.feign.server.service.StudentService;
import com.czc.cloud.feign.server.service.intf.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chen.zhangchao
 * @apiNote
 * @since 2020/1/8 11:00
 */
@RestController
@CrossOrigin
public class StudentController implements IStudentService {


    @Autowired
    private StudentService studentService;

    @Override
    public String getAllStudent() {
        return studentService.getAllStudent();
    }

    @Override
    public String saveStudent(Student student) {
        return studentService.saveStudent(student);
    }

    @PostMapping(value = "/myfeign/student")
    public String addStudent(@RequestParam(name = "str") String json){
        Student student = JSONObject.parseObject(json, Student.class);
        return studentService.saveStudent(student);
    }

}
