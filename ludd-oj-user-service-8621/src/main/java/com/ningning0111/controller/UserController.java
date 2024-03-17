package com.ningning0111.controller;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.model.dto.user.UserBanRequest;
import com.ningning0111.model.dto.user.UserQueryRequest;
import com.ningning0111.model.dto.user.UserUpdateRequest;
import com.ningning0111.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Project: com.ningning0111.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/11 19:21
 * @Description:
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public BaseResponse getUserInfo(@RequestHeader String authorization){
        String token = authorization.substring(7);
        return userService.getUserInfoByToken(token);
    }

    @GetMapping("/list/{page}/{size}")
    public BaseResponse getUsersInfo(
            @PathVariable int page,
            @PathVariable int size,
            UserQueryRequest request
    ){
        return userService.queryUser(request,page,size);
    }

    @DeleteMapping("/del/{questionId}")
    public BaseResponse deleteUser(
            @PathVariable Long questionId
    ){
        return userService.delUser(questionId);
    }

    @PostMapping("/update")
    public BaseResponse updateUser(
            @RequestBody UserUpdateRequest request
    ){
        return userService.updateUser(request);
    }

    @PostMapping("/ban")
    public BaseResponse banUser(
            @RequestBody UserBanRequest request
    ){
        return userService.banUser(request);
    }
}
