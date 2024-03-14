package com.ningning0111.listener;

import com.ningning0111.constant.RabbitQueue;
import com.ningning0111.model.entity.Submission;
import com.ningning0111.service.JudgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Project: com.ningning0111.lister
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/4 20:50
 * @Description:
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JudgeLister {
    private final JudgeService judgeService;
    @RabbitListener(queues = RabbitQueue.JUDGE_QUEUE)
    public void listenJudge(Submission submission){
        log.info("接收到判题任务:{}",submission);
        try {
            judgeService.judge(submission);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
