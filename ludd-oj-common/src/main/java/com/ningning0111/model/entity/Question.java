package com.ningning0111.model.entity;

import com.ningning0111.conver.JpaConverterListJson;
import com.ningning0111.model.enums.QuestionDifficultyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @Project: com.ningning0111.model.entity
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/1 20:33
 * @Description:
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "questions")
public class Question implements Serializable {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * 题目难度
     */
    @Enumerated(EnumType.STRING)
    private QuestionDifficultyType difficulty;

    /**
     * 标签列表
     */
    @Convert(converter = JpaConverterListJson.class)
    @Column(columnDefinition = "TEXT")
    private List<String> tags;

    /**
     * 题目提交数
     */
    private Integer submitNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;



    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

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

    private static final long serialVersionUID = 1L;

}
