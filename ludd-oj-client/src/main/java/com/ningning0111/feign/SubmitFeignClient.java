package com.ningning0111.feign;

import com.ningning0111.model.entity.Submission;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Project: com.ningning0111.feign
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/14 10:49
 * @Description:
 */
@FeignClient(name = "ludd-oj-submit-service",path = "/inner/submit")
public interface SubmitFeignClient {
    @PostMapping("/update")
    void updateSubmission(Submission submission);
}
