package com.ningning0111.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.model.dto.user.RegisterRequest;
import com.ningning0111.model.entity.User;
import com.ningning0111.repository.UserRepository;
import com.ningning0111.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @Project: com.ningning0111.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/7 10:01
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterServiceImpl implements RegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public BaseResponse register(RegisterRequest registerRequest) {
        String email = registerRequest.getEmail();
        User userByEmail = userRepository.findUserByEmail(email);
        if(userByEmail != null){
            return ResultUtils.success("邮箱已被注册");
        }
        String password = registerRequest.getPassword();
        String checkPassword = registerRequest.getCheckPassword();
        if(!StrUtil.equals(password,checkPassword)){
            return ResultUtils.success("两次密码不相等");
        }
        long currMillis = System.currentTimeMillis();
        User user = User.builder()
                .createTime(new Date(currMillis))
                .updateTime(new Date(currMillis))
                .nickname(registerRequest.getNickname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        userRepository.save(user);
        return ResultUtils.success("注册成功");
    }
}
