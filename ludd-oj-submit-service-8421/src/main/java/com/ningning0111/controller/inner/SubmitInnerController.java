package com.ningning0111.controller.inner;

import com.ningning0111.feign.SubmitFeignClient;
import com.ningning0111.model.entity.Submission;
import com.ningning0111.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Project: com.ningning0111.controller.inner
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/14 10:50
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/inner/submit")
public class SubmitInnerController implements SubmitFeignClient {
    private final SubmissionService service;
    @PostMapping("/update")
    public void updateSubmission(@RequestBody Submission submission){
        service.update(submission);
    }
}
