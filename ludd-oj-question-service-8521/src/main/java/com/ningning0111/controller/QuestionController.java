package com.ningning0111.controller;

import cn.hutool.core.util.StrUtil;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.model.dto.question.QuestionAddRequest;
import com.ningning0111.model.dto.question.QuestionQueryRequest;
import com.ningning0111.model.enums.QuestionDifficultyType;
import com.ningning0111.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Project: com.ningning0111.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/6 21:20
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/question")
@Slf4j
public class QuestionController {
    private final QuestionService service;

    @PostMapping("/add")
    public BaseResponse addQuestion(@RequestBody QuestionAddRequest request){
        if(request == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"请求参数为空");
        }
        String content = request.getContent();
        String title = request.getTitle();
        if(StrUtil.isBlank(content) || StrUtil.isBlank(title)){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        if(content.length() >= 10000 || title.length() >= 50){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"参数过长");
        }
        List<String> tags = request.getTags();
        if(tags == null || tags.isEmpty()){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"题目标签不准为空");
        }
        String difficulty = request.getDifficulty();
        if(difficulty == null || StrUtil.isBlank(difficulty)){
            request.setDifficulty(QuestionDifficultyType.EASY.getValue());
        }
        return service.addQuestion(request);
    }


    @GetMapping("/content/{page}/{size}")
    public BaseResponse queryQuestions(
            @PathVariable Integer page,
            @PathVariable Integer size,
            QuestionQueryRequest request
    ){
        return service.queryQuestionPage(request,page,size);
    }

    @GetMapping("/info/{questionId}")
    public BaseResponse queryQuestionById(
            @PathVariable Long questionId
    ){
        return service.queryQuestion(questionId);
    }
}
