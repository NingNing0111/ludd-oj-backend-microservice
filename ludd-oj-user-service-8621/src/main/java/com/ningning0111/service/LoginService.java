package com.ningning0111.service;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.model.dto.user.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @Project: com.ningning0111.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/7 09:30
 * @Description:
 */
public interface LoginService {
    BaseResponse login(LoginRequest dataRequest, HttpServletRequest httpRequest);
}
