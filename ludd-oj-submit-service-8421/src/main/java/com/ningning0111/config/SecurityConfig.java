package com.ningning0111.config;

import com.ningning0111.feign.UserFeignClient;
import com.ningning0111.filter.JwtAuthFilter;
import com.ningning0111.model.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Project: com.ningning0111.config
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/6 19:40
 * @Description:
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider provider;

    private static final String[] ALLOW_PATH = {
            "/inner/submit/**",
            "/api/submit/**",
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        // 所有请求均放行 鉴权放在网关处理
        httpSecurity.authorizeHttpRequests(req-> req
                .requestMatchers(ALLOW_PATH)
                .permitAll()
                .requestMatchers("/api/submit/add")
                .hasAnyAuthority(UserRole.USER.getValue(),UserRole.ADMIN.getValue(),UserRole.ROOT.getValue())
                .anyRequest()
                .authenticated()
        );
        httpSecurity.authenticationProvider(provider).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }


}
