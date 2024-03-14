package com.ningning0111.repository.specification;

import com.ningning0111.model.dto.submit.SubQueryRequest;
import com.ningning0111.model.entity.Submission;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project: com.ningning0111.repository.specification
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/13 00:00
 * @Description:
 */
public class SubmissionSpecification implements Specification<Submission> {
    private SubQueryRequest request;
    public SubmissionSpecification(SubQueryRequest request){
        this.request = request;
    }
    @Override
    public Predicate toPredicate(Root<Submission> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(request.getStatus() != null){
            predicates.add(criteriaBuilder.equal(root.get("status"),request.getStatus()));
        }
        if(request.getUserId() != null){
            predicates.add(criteriaBuilder.equal(root.get("userId"),request.getUserId()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
