package com.ningning0111.service.impl;

import com.alibaba.fastjson2.JSON;
import com.ningning0111.api.JudgeMachineApi;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.constant.RabbitQueue;
import com.ningning0111.feign.QuestionFeignClient;
import com.ningning0111.feign.SubmitFeignClient;
import com.ningning0111.model.dto.judge.JudgeConfig;
import com.ningning0111.model.dto.judge.JudgeRequest;
import com.ningning0111.model.entity.Submission;
import com.ningning0111.model.entity.TestCase;
import com.ningning0111.model.enums.SubmissionStatus;
import com.ningning0111.model.message.UpdateQMessage;
import com.ningning0111.model.vo.JudgeVo;
import com.ningning0111.service.JudgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project: com.ningning0111.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/5 13:14
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class JudgeServiceImpl implements JudgeService {
    private final SubmitFeignClient submitFeignClient;
    private final QuestionFeignClient questionFeignClient;
    private final JudgeMachineApi judgeMachineApi;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void judge(Submission submission) {

        // 对同一个提交记录修改判题状态
        submission.setStatus(SubmissionStatus.RUNNING.getValue());
        submitFeignClient.updateSubmission(submission);
        // 开始判题
        try {
            // 1. 获取测试用例
            TestCase testCase = questionFeignClient.queryTestcaseByQuestionId(submission.getQuestionId());
            JudgeConfig judgeConfig = new JudgeConfig();
            JudgeRequest request = JudgeRequest.builder()
                    .config(judgeConfig)
                    .inputData(testCase.getInputData())
                    .outputData(testCase.getOutputData())
                    .code(submission.getCode())
                    .config(JSON.parseObject(submission.getJudgeConfig(), JudgeConfig.class))
                    .language(submission.getLanguage())
                    .questionId(submission.getQuestionId())
                    .userId(submission.getUserId())
                    .build();

            BaseResponse<JudgeVo> judge = judgeMachineApi.judge(request);
            log.info("判题机响应:{}",judge);
            // 判题失败
            if (judge.getCode() == ErrorCode.SYSTEM_ERROR.getCode()) {
                submission.setStatus(SubmissionStatus.FAILED.getValue());
                log.error("判题失败：{}",submission.getId());
                submitFeignClient.updateSubmission(submission);
                return;
            }
            // 判题完成
            submission.setStatus(SubmissionStatus.SUCCEED.getValue());
            JudgeVo data = judge.getData();
            submission.setJudgeInfo(JSON.toJSONString(data.getJudgeInfo()));
            submission.setInput(data.getInputData());
            submission.setOutput(data.getUserOutput());
            submission.setPassInfo(data.getPassInfo());
            submission.setStatus(SubmissionStatus.SUCCEED.getValue());
            submitFeignClient.updateSubmission(submission);

        } catch (Exception e) {
            e.printStackTrace();
            submission.setStatus(SubmissionStatus.FAILED.getValue());
            submitFeignClient.updateSubmission(submission);
        } finally {
            if(isOk(submission)){
                UpdateQMessage updateQMessage = new UpdateQMessage(submission.getQuestionId(), isOk(submission));
                rabbitTemplate.convertAndSend(RabbitQueue.UPDATE_QUESTION_QUEUE,updateQMessage);
            }
        }
    }

    private boolean isOk(Submission submission){
        List<Boolean> passInfo = submission.getPassInfo();
        boolean isPass = true;
        if(passInfo==null || passInfo.isEmpty()){
            return false;
        }
        for(Boolean b: passInfo){
            if(!b){
                isPass = false;
                break;
            }
        }
        return isPass;
    }
}
