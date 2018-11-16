package com.petr.service.question;

import com.petr.persistence.entity.Question;
import com.petr.service.AbstractSearchSpecification;
import com.petr.transport.dto.question.QuestionFindDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class QuestionSearchSpecification extends AbstractSearchSpecification<Question> {

    public Specification<Question> questionFilter(QuestionFindDto dto) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "id", dto.getId()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "deleted", dto.getDeleted()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "required", dto.getRequired()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "text", dto.getText()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "min", dto.getMin()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "max", dto.getMax()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "type", dto.getType()));
            predicates.add(toEqualsPredicateId(root, criteriaBuilder, "survey", dto.getSurvey()));
            predicates.add(toEqualsPredicateIds(root, "answers", dto.getAnswers()));
            predicates.add(toPredicateBetween(root, criteriaBuilder, "date", dto.getStartDate(), dto.getFinishDate()));
            Object[] rawPredicates = predicates.stream().filter(Objects::nonNull).toArray();
            return criteriaBuilder.and(Arrays.copyOf(rawPredicates, rawPredicates.length, Predicate[].class));
        };
    }
}
