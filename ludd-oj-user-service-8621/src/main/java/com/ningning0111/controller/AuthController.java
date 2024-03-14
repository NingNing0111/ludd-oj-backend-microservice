package com.ningning0111.controller;

import cn.hutool.core.util.StrUtil;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.model.dto.user.LoginRequest;
import com.ningning0111.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
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
 * @Date: 2024/3/7 09:28
 * @Description:
 */
@RequestMapping("/api/user/login")
@RequiredArgsConstructor
@Slf4j
@RestController
public class AuthController {
    private final LoginService loginService;
    @PostMapping("/ep")
    public BaseResponse loginByEmailAndPassword(@RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest){
        if(loginRequest == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        log.info("===>{}",loginRequest);
        if(email ==null || StrUtil.isBlank(email) || password == null || StrUtil.isBlank(password)){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"邮箱或密码为空");
        }
        return loginService.login(loginRequest, httpServletRequest);
    }
}
