package com.ningning0111.model.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;


/**
 * @Project: com.ningning0111.model.vo
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/7 09:35
 * @Description:
 */
@Data
@Builder
public class AuthResultVO {
    private String token;
    private UserVO info;
    private Date authTime;
    private String ip;

}
