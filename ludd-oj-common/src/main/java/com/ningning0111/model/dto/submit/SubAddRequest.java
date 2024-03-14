package com.ningning0111.model.dto.submit;

import lombok.Data;

/**
 * @Project: com.ningning0111.model.dto.submit
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/6 19:55
 * @Description:
 */
@Data
public class SubAddRequest {
    private Long userId;
    private String language;
    private String code;
    private Long questionId;
    private String judgeConfig;
}
