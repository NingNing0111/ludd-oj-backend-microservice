package com.ningning0111.controller;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.model.dto.judge.JudgeRequest;
import com.ningning0111.model.vo.JudgeVo;
import com.ningning0111.service.JudgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: com.ningning0111.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/2 18:19
 * @Description:
 */
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class JudgeController {
    private final JudgeService judgeService;

    @PostMapping("/judge")
    public BaseResponse<JudgeVo> judge(@RequestBody JudgeRequest judgeRequest){
        if(judgeRequest == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"参数不能为空");
        }
        try {
            return judgeService.judge(judgeRequest);

        }catch (Exception e){
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR,e.getMessage());
        }
    }
}
