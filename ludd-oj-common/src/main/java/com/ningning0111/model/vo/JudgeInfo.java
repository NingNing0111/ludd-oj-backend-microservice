package com.ningning0111.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Project: com.ningning0111.model
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/2 18:15
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JudgeInfo implements Serializable {
    private String message;

    /**
     * 消耗内存（b）
     */
    private Long memory;

    /**
     * 消耗时间（ms）
     */
    private Long time;

}
