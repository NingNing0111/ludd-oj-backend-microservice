package com.ningning0111.api.sandbox;


import com.ningning0111.model.dto.execute.ExecuteCodeRequest;
import com.ningning0111.model.vo.ExecuteCodeResponse;

/**
 * @Project: com.ningning0111.api.sandbox
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/3 02:13
 * @Description:
 */
public abstract class SandboxApi {
    public abstract ExecuteCodeResponse execute(ExecuteCodeRequest executeCodeRequest);
}
