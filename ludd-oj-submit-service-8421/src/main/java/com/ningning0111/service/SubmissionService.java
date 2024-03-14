package com.ningning0111.service;

import com.ningning0111.common.BaseResponse;
import com.ningning0111.model.dto.submit.SubAddRequest;
import com.ningning0111.model.dto.submit.SubQueryRequest;
import com.ningning0111.model.entity.Submission;
import org.springframework.data.domain.Page;

/**
 * @Project: com.ningning0111.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/6 19:52
 * @Description:
 */
public interface SubmissionService {
    // 提交
    BaseResponse submit(SubAddRequest request);

    BaseResponse<Page<Submission>> querySubmission(SubQueryRequest request, Integer page, Integer size);

    void update(Submission submission);
}
