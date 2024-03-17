package com.ningning0111;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Project: com.ningning0111
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/13 23:41
 * @Description:
 */
@SpringBootApplication
@EnableFeignClients
public class QuestionServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuestionServiceApplication.class,args);
    }
}
