package com.ningning0111.model.dto.user;

import lombok.Data;

/**
 * @Project: com.ningning0111.model.dto.user
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/7 10:02
 * @Description:
 */
@Data
public class RegisterRequest {
    private String nickname;
    private String email;
    private String password;
    private String checkPassword;
}
