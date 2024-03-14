package com.ningning0111;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @Project: com.ningning0111
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/14 11:44
 * @Description:
 */
@SpringBootApplication(
        exclude = {
                DataSourceAutoConfiguration.class,
                SecurityAutoConfiguration.class,
                MongoAutoConfiguration.class
        }
)
public class JudgeMachineApplication {
    public static void main(String[] args) {
        SpringApplication.run(JudgeMachineApplication.class,args);
    }
}
