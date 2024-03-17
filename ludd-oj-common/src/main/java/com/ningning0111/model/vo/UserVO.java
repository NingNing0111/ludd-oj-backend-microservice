package com.ningning0111.model.vo;

import com.ningning0111.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @Project: com.ningning0111.model.vo
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/4 13:05
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private Long id;
    private String nickname;
    private String email;
    private UserRole role;
    private String userAvatar;
    private String userProfile;
    private Date createTime;
    private Date updateTime;
    private Boolean isUserBan;
}
