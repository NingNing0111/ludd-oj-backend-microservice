package com.ningning0111.model.dto.user;

import lombok.Data;

/**
 * @Project: com.ningning0111.model.dto.user
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/15 17:41
 * @Description:
 */
@Data
public class UserBanRequest {
    private Long userId;
    private boolean isBan = false;
}
