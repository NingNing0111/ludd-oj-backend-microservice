package com.ningning0111.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ningning0111.model.enums.UserRole;
import com.ningning0111.serializer.CustomAuthorityDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @Project: com.ningning0111.model.entity
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/2/29 10:34
 * @Description:
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
// user在PG中是保留字，因此使用users
@Table(name = "users")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 昵称
    @Column(nullable = false)
    private String nickname;
    // 邮箱
    @Column(nullable = false,unique = true)
    private String email;
    // 密码
    private String password;
    // 角色
    @Enumerated
    private UserRole role;
    // 头像地址
    @Column(nullable = false)
    private String userAvatar;
    // 个人简介
    private String userProfile;
    // 账号创建时间
    private Date createTime;
    // 账号信息更新时间
    private Date updateTime;
    // 是否封禁
    private Boolean isUserBan;
    // 是否删除
    private Boolean isDelete;

    private static final long serialVersionUID = 1L;

    @Override
    @JsonDeserialize(using = CustomAuthorityDeserializer.class)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(role.getValue());
        return List.of(new SimpleGrantedAuthority(role.getValue()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !this.isUserBan;
    }

    @PrePersist
    public void setDefaultValues(){
        if(isUserBan == null){
            isUserBan = false;
        }
        if(userAvatar == null){
            userAvatar = "https://a.520gexing.com/uploads/allimg/2023082315/lg1o542qkfm1.jpg";
        }
        if(isDelete == null){
            isDelete = false;
        }
        if(role == null){
            role = UserRole.USER;
        }
    }
}
