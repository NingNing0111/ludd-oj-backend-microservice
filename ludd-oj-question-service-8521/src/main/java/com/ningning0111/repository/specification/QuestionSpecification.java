package com.ningning0111.repository.specification;

import com.ningning0111.model.dto.question.QuestionQueryRequest;
import com.ningning0111.model.entity.Question;
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
 * @Date: 2024/3/6 22:01
 * @Description:
 */
public class QuestionSpecification implements Specification<Question> {

    private QuestionQueryRequest queryRequest;

    public QuestionSpecification(QuestionQueryRequest queryRequest){
        this.queryRequest = queryRequest;
    }
    @Override
    public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (queryRequest.getTitle() != null) {
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + queryRequest.getTitle() + "%"));
        }

        if (queryRequest.getTags() != null && !queryRequest.getTags().isEmpty()) {

            predicates.add(criteriaBuilder.equal(root.get("tags"),queryRequest.getTags()));
        }

        if (queryRequest.getDifficulty() != null) {
            predicates.add(criteriaBuilder.equal(root.get("difficulty"), queryRequest.getDifficulty()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }


}
