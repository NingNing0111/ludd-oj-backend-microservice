package com.ningning0111.model.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Project: com.ningning0111.model.message
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/13 16:29
 * @Description: 消息队列监听到的更新题目消息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateQMessage implements Serializable {
    /**
     * 题目ID
     */
    private Long questionId;
    /**
     * 是否通过
     */
    private boolean isPass;

}
