package com.ningning0111.model.dto.user;

import com.ningning0111.model.enums.UserRole;
import lombok.Data;

/**
 * @Project: com.ningning0111.model.dto.user
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/4 12:13
 * @Description:
 */
@Data
public class UserUpdateRequest {
    private Long userId;
    // 昵称
    private String nickname;
    // 角色
    private UserRole role;
    // 头像地址
    private String userAvatar;
    // 个人简介
    private String userProfile;
    // 密码
    private String password;
}
