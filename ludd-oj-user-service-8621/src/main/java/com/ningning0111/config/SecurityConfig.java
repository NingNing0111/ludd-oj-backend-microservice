package com.ningning0111.config;

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
            "/inner/user/**",
            "/api/user/register/**",
            "/api/user/login/**",
            "/api/user/generate/**",
    };

    private static final String[] ROOT_PATH = {
            "/api/user/del/**",
            "/api/user/update",
            "/api/user/ban",
            "/api/user/list/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        // 所有请求均放行 鉴权放在网关处理
        httpSecurity.authorizeHttpRequests(req-> req
                .requestMatchers(ALLOW_PATH)
                .permitAll()
                .requestMatchers(ROOT_PATH)
                .hasAnyAuthority(UserRole.ROOT.getValue())
                .anyRequest()
                .authenticated()
        );
        httpSecurity.authenticationProvider(provider).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
