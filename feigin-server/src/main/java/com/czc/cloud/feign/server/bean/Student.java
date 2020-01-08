package com.czc.cloud.feign.server.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author : chen.zhangchao
 * @apiNote
 * @since 2020/1/8 10:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;

    private String id;


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
