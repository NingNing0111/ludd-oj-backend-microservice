package com.ningning0111.controller;

import cn.hutool.core.util.StrUtil;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.model.dto.user.RegisterRequest;
import com.ningning0111.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: com.ningning0111.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/7 10:11
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/register")
@Slf4j
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("/user")
    public BaseResponse registerUser(@RequestBody RegisterRequest registerRequest){
        log.info("注册请求:{}",registerRequest);
        if(registerRequest == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        String email = registerRequest.getEmail();
        String nickname = registerRequest.getNickname();
        if(email == null || StrUtil.isBlank(email)){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"邮箱为空");
        }
        if(nickname == null || StrUtil.isBlank(nickname)){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"昵称为空");
        }
        return registerService.register(registerRequest);
    }
}
