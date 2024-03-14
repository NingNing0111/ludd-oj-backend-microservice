package com.ningning0111.model.dto.question;

import com.ningning0111.model.enums.QuestionDifficultyType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: com.ningning0111.model.dto.question
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/3 12:28
 * @Description:
 */
@Data
public class QuestionQueryRequest  implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 标题 模糊查询字段
     */
    private String title;


    /**
     * 标签列表 精准查询字段
     */
    private List<String> tags;

    /**
     * 难度 精准查询字段
     */
    private QuestionDifficultyType difficulty;

}

