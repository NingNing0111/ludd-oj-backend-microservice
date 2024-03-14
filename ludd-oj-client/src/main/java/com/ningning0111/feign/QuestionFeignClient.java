package com.ningning0111.feign;

import com.ningning0111.model.entity.Question;
import com.ningning0111.model.entity.TestCase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Project: com.ningning0111.feign
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/14 00:16
 * @Description:
 */
@FeignClient(name = "ludd-oj-question-service", path = "/inner/question")
public interface QuestionFeignClient {
    @GetMapping("/info/{questionId}")
    Question getQuestionById(
            @PathVariable Long questionId
    );

    @PostMapping("/update")
    void updateQuestion(
            @RequestBody Question question
    );

    @GetMapping("/testcase/{questionId}")
    TestCase queryTestcaseByQuestionId(
            @PathVariable Long questionId);
}
