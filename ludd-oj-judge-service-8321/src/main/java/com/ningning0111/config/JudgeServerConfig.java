package com.ningning0111.config;

import lombok.Data;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Project: com.ningning0111.config
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/5 10:32
 * @Description:
 */
@Configuration
@ConfigurationProperties(
        prefix = "judge"
)
@Data
public class JudgeServerConfig {
    private String judgeMachineUrl;
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
//

    @Bean
    public RabbitAdmin rabbitAdmin(RabbitTemplate rabbitTemplate){
        return new RabbitAdmin(rabbitTemplate);
    }
}
