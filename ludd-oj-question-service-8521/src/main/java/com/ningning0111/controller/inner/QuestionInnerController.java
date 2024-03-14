package com.ningning0111.controller.inner;

import com.ningning0111.feign.QuestionFeignClient;
import com.ningning0111.model.entity.Question;
import com.ningning0111.model.entity.TestCase;
import com.ningning0111.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Project: com.ningning0111.controller.inner
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/13 23:50
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/inner/question")
public class QuestionInnerController implements QuestionFeignClient {
    private final QuestionService questionService;

    @GetMapping("/info/{questionId}")
    public Question getQuestionById(
            @PathVariable Long questionId
    ){
        return questionService.queryQuestionById(questionId);
    }

    @PostMapping("/update")
    public void updateQuestion(
            @RequestBody Question question
    ){
        questionService.updateQuestion(question);
    }

    @GetMapping("/testcase/{questionId}")
    public TestCase queryTestcaseByQuestionId(
            @PathVariable Long questionId
    ){
        return questionService.queryTestcase(questionId);
    }
}
