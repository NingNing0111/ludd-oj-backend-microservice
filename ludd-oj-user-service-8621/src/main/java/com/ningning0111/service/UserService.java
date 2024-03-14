package com.ningning0111.service;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.model.dto.user.UserAddRequest;
import com.ningning0111.model.dto.user.UserUpdateRequest;
import com.ningning0111.model.entity.User;

/**
 * @Project: com.ningning0111.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/4 12:07
 * @Description:
 */
public interface UserService {

    /**
     * 添加用户
     * @param request
     * @return
     */
    BaseResponse addUser(UserAddRequest request);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    BaseResponse delUser(Long userId);

    /**
     * 修改用户信息
     * @param request
     * @return
     */
    BaseResponse updateUser(UserUpdateRequest request);

    /**
     * 查询用户
     * @param userId
     * @return
     */
    BaseResponse queryUser(Long userId);

    User findUserById(Long userId);

    BaseResponse getUserInfoByToken(String token);

    User findUserByEmail(String email);

}
