package com.ningning0111.repository;

import com.ningning0111.model.entity.Submission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Project: com.ningning0111.repository
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/6 19:58
 * @Description:
 */
@Repository
public interface SubmissionRepository extends JpaRepository<Submission,Long>, JpaSpecificationExecutor<Submission> {
    Page<Submission> findAll(Pageable pageable);
}
