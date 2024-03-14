package com.ningning0111.feign;

import com.ningning0111.config.JwtConfig;
import com.ningning0111.model.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Project: com.ningning0111.feign
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/14 00:15
 * @Description:
 */
@FeignClient(name = "ludd-oj-user-service",url = "/inner/user")
public interface UserFeignClient {
    @GetMapping("/get/{userId}")
    User queryUserById(@PathVariable Long userId);
    @GetMapping("/config/jwt")
    JwtConfig getJwtConfig();
    @GetMapping("/get/email/{email}")
    User queryUserByEmail(@PathVariable String email);
}
