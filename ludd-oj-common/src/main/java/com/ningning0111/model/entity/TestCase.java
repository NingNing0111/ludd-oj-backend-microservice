package com.ningning0111.model.entity;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: com.ningning0111.model
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/3 19:31
 * @Description:
 */

@Document
@Data
@Builder
public class TestCase implements Serializable {
    /**
     * 测试用例ID
     */
    @Id
    private String id;

    /**
     * 题目ID
     */
    @Indexed(unique = true)
    private Long questionId;

    /**
     * 输入数据
     */
    private List<String> inputData;

    /**
     * 输出数据
     */
    private List<String> outputData;


    @Transient
    private static final long serialVersionUID = 1L;


}
