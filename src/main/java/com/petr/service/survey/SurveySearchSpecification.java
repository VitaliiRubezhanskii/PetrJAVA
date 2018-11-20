package com.petr.service.survey;

import com.petr.persistence.entity.survey.Survey;
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
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "status", dto.getStatus()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "name", dto.getName()));
            predicates.add(toEqualsPredicateIds(root, "questions", dto.getQuestions()));
            predicates.add(toEqualsPredicateIds(root, "surveyLimits", dto.getSurveyLimits()));
            predicates.add(toPredicateBetween(root, criteriaBuilder, "date", dto.getStartDate(), dto.getFinishDate()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "count", dto.getCount()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "passed", dto.getPassed()));
            Object[] rawPredicates = predicates.stream().filter(Objects::nonNull).toArray();
            return criteriaBuilder.and(Arrays.copyOf(rawPredicates, rawPredicates.length, Predicate[].class));
        };
    }
}
