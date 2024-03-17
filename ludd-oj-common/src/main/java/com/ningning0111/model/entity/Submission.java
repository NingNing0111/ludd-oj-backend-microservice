package com.ningning0111.model.entity;

import com.ningning0111.conver.JpaConverterListJson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Project: com.ningning0111.model.entity
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/3 12:08
 * @Description:
 */
@Entity
@Data
@Table(name = "submissions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Submission implements Serializable {
        /**
         * id
         */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        /**
         * 编程语言
         */
        private String language;

        /**
         * 用户代码
         */
        @Column(columnDefinition = "TEXT")
        private String code;

        private String judgeConfig;

        /**
         * 判题信息（json 对象）
         */
        private String judgeInfo;

        /**
         * 判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）
         */
        private Integer status;

        /**
         * 题目 id
         */
        private Long questionId;

        /**
         * 创建用户 id
         */
        private Long userId;

        /**
         * 创建时间
         */
        private Date createTime;

        /**
         * 更新时间
         */
        private Date updateTime;

        /**
         * 是否删除
         */
        private Integer isDelete;

        @Convert(converter = JpaConverterListJson.class)
        @Column(columnDefinition = "TEXT")
        private List<String> input;

        @Column(columnDefinition = "TEXT")
        @Convert(converter = JpaConverterListJson.class)
        private List<String> output;

        @Convert(converter = JpaConverterListJson.class)
        private List<Boolean> passInfo;
        private static final long serialVersionUID = 1L;

}
