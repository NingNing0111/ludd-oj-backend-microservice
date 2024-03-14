package com.ningning0111.api;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.config.JudgeServerConfig;
import com.ningning0111.model.dto.judge.JudgeRequest;
import com.ningning0111.model.vo.JudgeVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Project: com.ningning0111.api
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/5 10:32
 * @Description:
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JudgeMachineApi {
    private final JudgeServerConfig config;
    private final RestTemplate restTemplate;

    public BaseResponse<JudgeVo> judge(JudgeRequest request){
        String judgeBaseUrl = config.getJudgeMachineUrl() + "/judge";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JudgeRequest> httpEntity = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<BaseResponse<JudgeVo>> response = restTemplate.exchange(
                judgeBaseUrl,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<BaseResponse<JudgeVo>>() {}
        );

        return response.getBody();

    }
}
