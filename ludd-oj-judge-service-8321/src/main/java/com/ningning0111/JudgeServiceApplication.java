package com.ningning0111;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Project: com.ningning0111
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/14 10:03
 * @Description:
 */
@SpringBootApplication(
        exclude = {
                SecurityAutoConfiguration.class,
                DataSourceAutoConfiguration.class,
                MongoAutoConfiguration.class
        }
)
@EnableFeignClients
public class JudgeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(JudgeServiceApplication.class,args);
    }
}
