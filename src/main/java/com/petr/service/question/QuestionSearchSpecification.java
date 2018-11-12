package com.petr.service.question;

import com.petr.persistence.entity.Question;
import com.petr.persistence.entity.Survey;
import com.petr.transport.dto.question.QuestionFindDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public interface QuestionSearchSpecification {
    static Specification<Question> questionFilter(QuestionFindDto dto) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "id", dto.getId()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "deleted", dto.getDeleted()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "text", dto.getText()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "type", dto.getType()));
            predicates.add(toEqualsPredicateId(root, criteriaBuilder, "survey", dto.getSurvey()));
            predicates.add(toEqualsPredicate(root, "answers", dto.getAnswers()));
            Object[] rawPredicates = predicates.stream().filter(Objects::nonNull).toArray();
            return criteriaBuilder.and(Arrays.copyOf(rawPredicates, rawPredicates.length, Predicate[].class));
        };
    }

    static Predicate toEqualsPredicate(Root<Question> root, CriteriaBuilder criteriaBuilder, String param, Object paramValue) {
        return paramValue != null ? criteriaBuilder.equal(root.get(param), paramValue) : null;
    }

    static Predicate toLikePredicate(Root<Question> root, CriteriaBuilder criteriaBuilder, String param, Object paramValue) {
        return paramValue != null ? criteriaBuilder.like(root.get(param), "%" + paramValue + "%") : null;
    }

    static Predicate toEqualsPredicateId(Root<Question> root, CriteriaBuilder criteriaBuilder, String param, Long paramValue) {
        return paramValue != null ? criteriaBuilder.equal(root.get(param).get("id"), paramValue) : null;
    }

    static Predicate toEqualsPredicate(Root<Question> root, String param, List<Long> paramValue) {
        return paramValue != null ? root.join(param).get("id").in(paramValue) : null;
    }
}
