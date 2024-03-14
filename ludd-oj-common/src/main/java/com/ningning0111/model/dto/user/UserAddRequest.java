package com.ningning0111.model.dto.user;

import lombok.Data;

/**
 * @Project: com.ningning0111.model.dto.user
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/4 12:10
 * @Description:
 */
@Data
public class UserAddRequest {
    // 昵称
    private String nickname;
    // 邮箱
    private String email;
    // 密码
    private String password;
}
