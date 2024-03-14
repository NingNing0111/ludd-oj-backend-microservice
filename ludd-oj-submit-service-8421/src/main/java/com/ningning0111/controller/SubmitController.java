package com.ningning0111.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.model.dto.judge.JudgeConfig;
import com.ningning0111.model.dto.submit.SubAddRequest;
import com.ningning0111.model.dto.submit.SubQueryRequest;
import com.ningning0111.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Project: com.ningning0111.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/6 20:20
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/submit")
public class SubmitController {
    private final SubmissionService service;

    @PostMapping("/add")
    public BaseResponse submit(@RequestBody SubAddRequest request){
        if(request == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        if(StrUtil.isBlank(request.getCode())){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"代码为空");
        }
        if(StrUtil.isBlank(request.getLanguage())){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"编程语言为空");
        }
        if(request.getQuestionId() == null || request.getUserId() == null) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"ID信息缺失");
        }
        if(request.getJudgeConfig() == null){
            request.setJudgeConfig(JSON.toJSONString(new JudgeConfig()));
        }
        return service.submit(request);
    }

    @GetMapping("/{page}/{size}")
    public BaseResponse querySubmission(
            @PathVariable Integer page,
            @PathVariable Integer size,
            SubQueryRequest request
    ){
        return service.querySubmission(request,page,size);
    }
}

