package com.czc.cloud.feign.server.service;

import com.alibaba.fastjson.JSONArray;
import com.czc.cloud.feign.server.bean.Student;
import com.czc.cloud.feign.server.service.intf.IStudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : chen.zhangchao
 * @apiNote
 * @since 2020/1/8 10:58
 */
@Service
public class StudentService implements IStudentService {

    private static List<Student> studentList = new ArrayList<>();
    @Override
    public String getAllStudent() {
        return JSONArray.toJSONString(studentList);
    }

    @Override
    public String saveStudent(Student student) {
        studentList.add(student);
        return "ok";
    }
}
