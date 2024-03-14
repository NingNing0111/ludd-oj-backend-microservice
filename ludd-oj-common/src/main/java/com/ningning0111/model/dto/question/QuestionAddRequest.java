package com.ningning0111.model.dto.question;

import com.ningning0111.model.enums.QuestionDifficultyType;
import lombok.Data;

import java.util.List;

/**
 * @Project: com.ningning0111.model.dto.question
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/3 12:32
 * @Description:
 */
@Data
public class QuestionAddRequest {
    // 题目标题
    private String title;
    // 题目描述
    private String content;
    // 难度等级
    private String difficulty = QuestionDifficultyType.EASY.getValue();
    // 标签列表
    private List<String> tags;
    // 创建人Id
    private Long userId;
    // 题目的输入用例
    private List<String> inputData;
    // 题目的输出用例
    private List<String> outputData;
}
