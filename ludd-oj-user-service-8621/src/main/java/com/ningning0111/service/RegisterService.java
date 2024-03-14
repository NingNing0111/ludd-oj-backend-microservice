package com.ningning0111.service;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.model.dto.user.RegisterRequest;

/**
 * @Project: com.ningning0111.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/7 10:01
 * @Description:
 */
public interface RegisterService {
    BaseResponse register(RegisterRequest registerRequest);
}
