package com.ningning0111.model.dto.judge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project: com.ningning0111.model.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/3 02:18
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JudgeConfig {
    private String sandboxName = "luddSandbox";
    /**
     * 时间限制大小 单位ms
     */
    private Long maxTime = 2000L;
    /**
     * 内存限制大小 单位MB -> 1048576B
     */
    private Long maxMemory = 100048576L;

}
