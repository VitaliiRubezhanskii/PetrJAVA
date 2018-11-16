package com.petr.service.answer;

import com.petr.persistence.entity.Answer;
import com.petr.service.AbstractSearchSpecification;
import com.petr.transport.dto.answer.AnswerFindDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class AnswerSearchSpecification extends AbstractSearchSpecification<Answer> {
    Specification<Answer> answerFilter(AnswerFindDto dto) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "id", dto.getId()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "deleted", dto.getDeleted()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "text", dto.getText()));
            predicates.add(toEqualsPredicateId(root, criteriaBuilder, "question", dto.getQuestion()));
            predicates.add(toPredicateBetween(root, criteriaBuilder, "date", dto.getStartDate(), dto.getFinishDate()));
            Object[] rawPredicates = predicates.stream().filter(Objects::nonNull).toArray();
            return criteriaBuilder.and(Arrays.copyOf(rawPredicates, rawPredicates.length, Predicate[].class));
        };
    }
}
