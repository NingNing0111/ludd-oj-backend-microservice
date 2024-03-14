package com.ningning0111.model.enums;

import org.springframework.util.ObjectUtils;

/**
 * @Project: com.ningning0111.model.enums
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/1 23:54
 * @Description:
 */
public enum QuestionDifficultyType {
    /**
     * 简单题
     */
    EASY("easy"),
    /**
     * 中等题
     */
    MEDIUM("medium"),
    /**
     * 困难题
     */
    HARD("hard");
    private String value;
    QuestionDifficultyType(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }

    public static QuestionDifficultyType getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (QuestionDifficultyType anEnum : QuestionDifficultyType.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}
