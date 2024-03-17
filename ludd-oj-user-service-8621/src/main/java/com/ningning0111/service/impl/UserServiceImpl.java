package com.ningning0111.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.model.dto.user.UserAddRequest;
import com.ningning0111.model.dto.user.UserBanRequest;
import com.ningning0111.model.dto.user.UserQueryRequest;
import com.ningning0111.model.dto.user.UserUpdateRequest;
import com.ningning0111.model.entity.User;
import com.ningning0111.model.enums.UserRole;
import com.ningning0111.model.vo.UserVO;
import com.ningning0111.repository.UserRepository;
import com.ningning0111.repository.specification.UserSpecification;
import com.ningning0111.service.UserService;
import com.ningning0111.util.JwtUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    private String password;
    @Value("${user.root.password}")
    public void setRootPassword(String password){
        this.password = password;
    }
    /**
     * 初始化一个Root用户
     */
    @PostConstruct
    public void initRoot(){
        User user = new User();
        long time = System.currentTimeMillis();
        user.setNickname("root");
        user.setEmail("example@ludd-oj.com");
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(UserRole.ROOT);
        user.setCreateTime(new Date(time));
        user.setUpdateTime(new Date(time));
        userRepository.save(user);
    }

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
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setIsDelete(true);
            userRepository.save(user);
            return ResultUtils.success("删除成功");
        }else{
            return ResultUtils.error(ErrorCode.NOT_FOUND_ERROR);
        }
    }

    @Override
    public BaseResponse updateUser(UserUpdateRequest request) {
        Long userId = request.getUserId();
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(request.getUserAvatar() != null){
                user.setUserProfile(request.getUserAvatar());
            }
            if(request.getUserProfile() != null){
                user.setUserProfile(request.getUserProfile());
            }
            if(request.getNickname() != null){
                user.setNickname(request.getNickname());
            }
            if(request.getRole() != null){
                user.setRole(request.getRole());
            }
            if (request.getPassword() != null){
                user.setPassword(passwordEncoder.encode(request.getPassword()));
            }
            userRepository.save(user);
            return ResultUtils.success("修改成功");
        }
        return ResultUtils.error(ErrorCode.OPERATION_ERROR);
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
    public BaseResponse queryUser(UserQueryRequest request,int page,int size) {

        UserSpecification userSpecification = new UserSpecification(request);

        Page<User> users = userRepository.findAll(userSpecification, PageRequest.of(page, size));
        return ResultUtils.success(users);
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

    @Override
    public BaseResponse banUser(UserBanRequest userBanRequest) {
        Long userId = userBanRequest.getUserId();
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setIsUserBan(userBanRequest.isBan());
            userRepository.save(user);
            return ResultUtils.success("账号状态修改成功");
        }
        return ResultUtils.error(ErrorCode.OPERATION_ERROR);
    }


}
