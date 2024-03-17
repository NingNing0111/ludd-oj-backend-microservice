package com.ningning0111.service.impl;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.constant.RabbitQueue;
import com.ningning0111.feign.QuestionFeignClient;
import com.ningning0111.model.dto.submit.SubAddRequest;
import com.ningning0111.model.dto.submit.SubQueryRequest;
import com.ningning0111.model.entity.Question;
import com.ningning0111.model.entity.Submission;
import com.ningning0111.model.enums.SubmissionStatus;
import com.ningning0111.repository.SubmissionRepository;
import com.ningning0111.repository.specification.SubmissionSpecification;
import com.ningning0111.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @Project: com.ningning0111.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/6 20:09
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final RabbitTemplate rabbitTemplate;
    private final QuestionFeignClient questionFeignClient;
    @Override
    public BaseResponse submit(SubAddRequest request) {
        Submission submission = Submission.builder()
                .userId(request.getUserId())
                .code(request.getCode())
                .language(request.getLanguage())
                .questionId(request.getQuestionId())
                .createTime(new Date(System.currentTimeMillis()))
                .updateTime(new Date(System.currentTimeMillis()))
                .status(SubmissionStatus.WAITING.getValue())
                .isDelete(0)
                .judgeConfig(request.getJudgeConfig())
                .build();
        submission = submissionRepository.save(submission);
        log.info("===>{}",submission);
        rabbitTemplate.convertAndSend(RabbitQueue.JUDGE_QUEUE,submission);
        Question question = questionFeignClient.getQuestionById(submission.getQuestionId());
        question.setSubmitNum(question.getSubmitNum() + 1);
        questionFeignClient.updateQuestion(question);
        return ResultUtils.success("提交成功");
    }

    @Override
    public BaseResponse<Page<Submission>> querySubmission(SubQueryRequest request, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        SubmissionSpecification spe = new SubmissionSpecification(request);
        Page<Submission> pageResult = submissionRepository.findAll(spe,pageRequest);
        return ResultUtils.success(pageResult);
    }

    @Override
    public void update(Submission submission) {
        submissionRepository.save(submission);
    }
}
