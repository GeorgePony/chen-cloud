package com.czc.cloud.feign.client.fallback;

import com.czc.cloud.feign.client.feign.StudentService;
import org.springframework.stereotype.Component;

/**
 * @author : chen.zhangchao
 * @apiNote
 * @since 2020/1/8 11:58
 */
@Component
public class StudentServiceFallback implements StudentService {
    @Override
    public String getAllStudent() {
        return "请求超时，请重试";
    }

    @Override
    public String saveStudent(String str) {
        return "请求超时，请重试";
    }
}
