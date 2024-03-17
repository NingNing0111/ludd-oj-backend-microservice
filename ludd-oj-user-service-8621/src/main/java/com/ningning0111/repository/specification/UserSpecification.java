package com.ningning0111.repository.specification;

import com.ningning0111.model.dto.question.QuestionQueryRequest;
import com.ningning0111.model.dto.user.UserQueryRequest;
import com.ningning0111.model.entity.Question;
import com.ningning0111.model.entity.User;
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
public class UserSpecification implements Specification<User> {

    private UserQueryRequest queryRequest;

    public UserSpecification(UserQueryRequest queryRequest){
        this.queryRequest = queryRequest;
    }
    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (queryRequest.getEmail() != null) {
            predicates.add(criteriaBuilder.like(root.get("email"), "%" + queryRequest.getEmail() + "%"));
        }

        if (queryRequest.getUserId() != null) {

            predicates.add(criteriaBuilder.equal(root.get("id"),queryRequest.getUserId()));
        }

        if (queryRequest.getUserRole() != null) {
            predicates.add(criteriaBuilder.equal(root.get("role"), queryRequest.getUserRole()));
        }
        // 删除的数据不加入返回结果中
        predicates.add(criteriaBuilder.equal(root.get("isDelete"),false));

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }


}
