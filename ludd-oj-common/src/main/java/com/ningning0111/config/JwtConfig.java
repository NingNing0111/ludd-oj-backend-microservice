package com.ningning0111.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: com.ningning0111.config
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/7 08:24
 * @Description:
 */
@Configuration
@ConfigurationProperties(
        prefix = "application.security.jwt"
)
@Data
public class JwtConfig {
    /**
     * 密钥
     */
    private String secretKey = "CF333EAFA6359B8CBED261DDB964E57C8075A2976CD8A490DD8FEC1D97C2CF18";
    /**
     * 过期时间 一天
     */
    private Long jwtExpiration = 1000L * 60 * 60 * 24L;

}
