package com.czc.cloud.feign.server.service.intf;

import com.czc.cloud.feign.server.bean.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author : chen.zhangchao
 * @apiNote 学生类
 * @since 2020/1/8 10:55
 */
public interface IStudentService {


    @GetMapping(value = "/myfeign/student")
    String getAllStudent();

    @PostMapping(value = "/myfeign/student1")
    String saveStudent(@RequestBody Student student);
}
