package com.ningning0111.repository;

import com.ningning0111.model.entity.TestCase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Project: com.ningning0111.repository
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/3 19:50
 * @Description:
 */
@Repository
public interface TestCaseRepository extends MongoRepository<TestCase, String> {
    TestCase queryTestCaseByQuestionId(Long questionId);

    void deleteTestCaseByQuestionId(Long questionId);
}
