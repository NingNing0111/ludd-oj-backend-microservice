package com.ningning0111.filter;

import com.ningning0111.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @Project: com.ningning0111.filter
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/7 08:37
 * @Description:
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("authorization");
        final String jwt;
        final String username;
        // 如果请求头authorization信息不存在
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            log.info("authorization不存在");
            filterChain.doFilter(request,response);
            return;
        }
        jwt = authHeader.substring(7);
        log.info("jwt:{}",jwt);
        try {
            username = jwtUtils.extractEmail(jwt);
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                log.info("{}",userDetails.getAuthorities());
                if(jwtUtils.isTokenValid(jwt,userDetails)){
                    UsernamePasswordAuthenticationToken authTokenAuth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authTokenAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authTokenAuth);
                    log.info("校验成功");
                }
            }
        }catch (Exception e){
            log.info("{}",e.getMessage());
        }finally {
            filterChain.doFilter(request,response);
        }

    }
}
