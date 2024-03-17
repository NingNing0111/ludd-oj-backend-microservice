package com.ningning0111.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class TestCase implements Serializable {


    /**
     * 题目ID
     */
    @Id
    private Long id;

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
