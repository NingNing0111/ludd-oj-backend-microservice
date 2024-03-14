package com.ningning0111.listener;

import com.ningning0111.constant.RabbitQueue;
import com.ningning0111.model.message.UpdateQMessage;
import com.ningning0111.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Project: com.ningning0111.listener
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/13 14:22
 * @Description:
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class QuestionListener {
    private final QuestionService questionService;
    @RabbitListener(queues = RabbitQueue.UPDATE_QUESTION_QUEUE)
    public void updateQuestions(UpdateQMessage updateQMessage){
        try {
            log.info("{}",updateQMessage);
            if(updateQMessage.isPass()){
                questionService.addPassNumQuestion(updateQMessage.getQuestionId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
