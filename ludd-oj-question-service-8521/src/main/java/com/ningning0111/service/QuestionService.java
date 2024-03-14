package com.ningning0111.service;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.model.dto.question.QuestionAddRequest;
import com.ningning0111.model.dto.question.QuestionQueryRequest;
import com.ningning0111.model.entity.Question;
import com.ningning0111.model.entity.TestCase;

/**
 * @Project: com.ningning0111.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/3 12:22
 * @Description:
 */
public interface QuestionService {

    /**
     * 添加题目
     * @param request
     * @return
     */
    BaseResponse addQuestion(QuestionAddRequest request);

    /**
     * 删除题目
     * @param questionId
     * @return
     */
    BaseResponse deleteQuestion(Long questionId);

    /**
     * 查询题目
     * @param request
     * @return
     */
    BaseResponse queryQuestionPage(QuestionQueryRequest request, Integer page, Integer size);

    BaseResponse queryQuestion(Long questionId);

    void addPassNumQuestion(Long questionId);

    Question queryQuestionById(Long questionId);

    void updateQuestion(Question question);

    TestCase queryTestcase(Long questionId);
}
