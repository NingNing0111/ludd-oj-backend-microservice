package com.ningning0111.model.dto.user;

import com.ningning0111.model.enums.UserRole;
import lombok.Builder;
import lombok.Data;

/**
 * @Project: com.ningning0111.model.dto.user
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/15 15:22
 * @Description:
 */
@Data
public class UserQueryRequest {
    private String email;
    private Long userId;
    private UserRole userRole;
    private boolean isUserBan;
}
