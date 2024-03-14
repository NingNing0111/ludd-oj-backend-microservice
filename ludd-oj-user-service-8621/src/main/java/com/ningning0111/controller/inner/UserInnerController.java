package com.ningning0111.controller.inner;

import com.ningning0111.config.JwtConfig;
import com.ningning0111.feign.UserFeignClient;
import com.ningning0111.model.entity.User;
import com.ningning0111.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Project: com.ningning0111.controller.inner
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/13 23:33
 * @Description:
 */
@RestController
@RequestMapping("/inner/user")
@RequiredArgsConstructor
public class UserInnerController implements UserFeignClient {
    private final JwtConfig jwtConfig;

    private final UserService userService;

    @GetMapping("/get/{userId}")
    public User queryUserById(
            @PathVariable Long userId
    ){
        return userService.findUserById(userId);
    }

    @GetMapping("/get/email/{email}")
    public User queryUserByEmail(
            @PathVariable String email
    ){
        return userService.findUserByEmail(email);
    }

    @GetMapping("/config/jwt")
    public JwtConfig getJwtConfig(){
        return jwtConfig;
    }
}
