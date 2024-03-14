package com.ningning0111.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.model.dto.user.UserAddRequest;
import com.ningning0111.model.dto.user.UserUpdateRequest;
import com.ningning0111.model.entity.User;
import com.ningning0111.model.enums.UserRole;
import com.ningning0111.model.vo.UserVO;
import com.ningning0111.repository.UserRepository;
import com.ningning0111.service.UserService;
import com.ningning0111.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

/**
 * @Project: com.ningning0111.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/4 12:07
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Override
    public BaseResponse addUser(UserAddRequest request) {
        String nickName = request.getNickname();
        String email = request.getEmail();
        String password = request.getPassword();
        if(StrUtil.isBlank(nickName) || StrUtil.isBlank(email) || StrUtil.isBlank(password)){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"信息不能为空");
        }
        User userByEmail = userRepository.findUserByEmail(email);
        if(userByEmail != null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"邮箱已被注册");
        }
        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickname(nickName)
                .role(UserRole.USER)
                .createTime(new Date(System.currentTimeMillis()))
                .updateTime(new Date(System.currentTimeMillis())) // millis可能会不等
                .build();
        userRepository.save(user);
        return ResultUtils.success("添加成功");
    }

    @Override
    public BaseResponse delUser(Long userId) {
        userRepository.deleteById(userId);
        return ResultUtils.success("删除成功!");
    }

    @Override
    public BaseResponse updateUser(UserUpdateRequest request) {
        return null;
    }

    @Override
    public BaseResponse queryUser(Long userId) {
        UserVO userVo = new UserVO();
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            BeanUtils.copyProperties(user,userVo);
        }
        return ResultUtils.success(userVo);
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }

    @Override
    public BaseResponse getUserInfoByToken(String token) {
        String email = jwtUtils.extractEmail(token);
        User user = userRepository.findUserByEmail(email);
        if(user == null){
            return ResultUtils.error(ErrorCode.NOT_FOUND_ERROR);
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return ResultUtils.success(userVO);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


}
