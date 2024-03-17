package com.ningning0111.service.impl;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.model.dto.user.LoginRequest;
import com.ningning0111.model.entity.User;
import com.ningning0111.model.vo.AuthResultVO;
import com.ningning0111.model.vo.UserVO;
import com.ningning0111.repository.UserRepository;
import com.ningning0111.service.LoginService;
import com.ningning0111.util.JwtUtils;
import com.ningning0111.util.NetUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * @Project: com.ningning0111.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/7 09:31
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;

    @Override
    public BaseResponse login(LoginRequest dataRequest, HttpServletRequest httpRequest) {
        User user = userRepository.findUserByEmail(dataRequest.getEmail());
        if (user == null) {
            return ResultUtils.error(ErrorCode.NOT_FOUND_ERROR, "用户不存在");
        }
        try {
            // 验证邮箱和密码
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dataRequest.getEmail(),
                            dataRequest.getPassword()
                    )
            );
            // 构建VO
            UserVO userVO = UserVO.builder()
                    .email(user.getEmail())
                    .role(user.getRole())
                    .nickname(user.getNickname())
                    .updateTime(user.getUpdateTime())
                    .createTime(user.getCreateTime())
                    .id(user.getId())
                    .isUserBan(user.getIsUserBan())
                    .userAvatar(user.getUserAvatar())
                    .userProfile(user.getUserProfile())
                    .build();
            String token = jwtUtils.generateToken(user);
            String ipAddress = NetUtils.getIpAddress(httpRequest);
            AuthResultVO authResultVO = AuthResultVO.builder()
                    .authTime(new Date(System.currentTimeMillis()))
                    .ip(ipAddress)
                    .token(token)
                    .info(userVO)
                    .build();
            return ResultUtils.success(authResultVO);

        } catch (Exception e) {
            return ResultUtils.error(ErrorCode.OPERATION_ERROR,"用户名或密码错误");
        }

    }
}
