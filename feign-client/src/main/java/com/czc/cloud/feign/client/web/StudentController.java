package com.czc.cloud.feign.client.web;

import com.alibaba.fastjson.JSONObject;
import com.czc.cloud.feign.client.bean.Student;
import com.czc.cloud.feign.client.feign.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : chen.zhangchao
 * @apiNote
 * @since 2020/1/8 11:14
 */
@RestController
@CrossOrigin
public class StudentController  implements StudentService{

    @Autowired
    private StudentService studentService;

    @Override
    public String getAllStudent() {
        return studentService.getAllStudent();
    }


    @Override
    public String saveStudent(@RequestParam(name = "str") String str){
        return studentService.saveStudent(str);
    }
}
