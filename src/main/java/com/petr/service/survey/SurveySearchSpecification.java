package com.petr.service.survey;

import com.petr.persistence.entity.Survey;
import com.petr.service.AbstractSearchSpecification;
import com.petr.transport.dto.survey.SurveyFindDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class SurveySearchSpecification extends AbstractSearchSpecification<Survey> {
    Specification<Survey> surveyFilter(SurveyFindDto dto) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "id", dto.getId()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "deleted", dto.getDeleted()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "name", dto.getName()));
            predicates.add(toEqualsPredicateIds(root, "questions", dto.getQuestions()));
            predicates.add(toPredicateBetween(root, criteriaBuilder, "date", dto.getStartDate(), dto.getFinishDate()));
            Object[] rawPredicates = predicates.stream().filter(Objects::nonNull).toArray();
            return criteriaBuilder.and(Arrays.copyOf(rawPredicates, rawPredicates.length, Predicate[].class));
        };
    }
}
