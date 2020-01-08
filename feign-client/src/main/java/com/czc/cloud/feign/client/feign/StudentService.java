package com.czc.cloud.feign.client.feign;

import com.czc.cloud.feign.client.bean.Student;
import com.czc.cloud.feign.client.fallback.StudentServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : chen.zhangchao
 * @apiNote
 * @since 2020/1/8 11:13
 */
@FeignClient(name="feign-server-app" ,fallback = StudentServiceFallback.class)
public interface StudentService {
        @GetMapping(value = "/myfeign/student")
    String getAllStudent();

    /**
     * 添加学生
     * @param str
     * @return
     */
    @PostMapping(value = "/myfeign/student")
    String saveStudent(@RequestParam(name = "str") String str);
}
