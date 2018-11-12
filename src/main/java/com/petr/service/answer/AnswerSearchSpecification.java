package com.petr.service.answer;

import com.petr.persistence.entity.Answer;
import com.petr.transport.dto.answer.AnswerFindDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public interface AnswerSearchSpecification {
    static Specification<Answer> answerFilter(AnswerFindDto dto) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "id", dto.getId()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "deleted", dto.getDeleted()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "text", dto.getText()));
            predicates.add(toEqualsPredicateId(root, criteriaBuilder, "question", dto.getQuestion()));
            Object[] rawPredicates = predicates.stream().filter(Objects::nonNull).toArray();
            return criteriaBuilder.and(Arrays.copyOf(rawPredicates, rawPredicates.length, Predicate[].class));
        };
    }

    static Predicate toEqualsPredicate(Root<Answer> root, CriteriaBuilder criteriaBuilder, String param, Object paramValue) {
        return paramValue != null ? criteriaBuilder.equal(root.get(param), paramValue) : null;
    }

    static Predicate toLikePredicate(Root<Answer> root, CriteriaBuilder criteriaBuilder, String param, Object paramValue) {
        return paramValue != null ? criteriaBuilder.like(root.get(param), "%" + paramValue + "%") : null;
    }

    static Predicate toEqualsPredicateId(Root<Answer> root, CriteriaBuilder criteriaBuilder, String param, Long paramValue) {
        return paramValue != null ? criteriaBuilder.equal(root.get(param).get("id"), paramValue) : null;
    }
}
