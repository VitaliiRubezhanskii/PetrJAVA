package com.petr.service.surveyLimit;

import com.petr.persistence.entity.survey.SurveyLimit;
import com.petr.service.AbstractSearchSpecification;
import com.petr.transport.dto.survetLimit.SurveyLimitFindDto;
import com.petr.transport.dto.survey.SurveyFindDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class SurveyLimitSearchSpecification extends AbstractSearchSpecification<SurveyLimit> {
    Specification<SurveyLimit> surveyLimitFilter(SurveyLimitFindDto dto) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "id", dto.getId()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "status", dto.getStatus()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "location", dto.getLocation()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "minAge", dto.getMinAge()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "maxAge", dto.getMaxAge()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "gender", dto.getGender()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "count", dto.getCount()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "passed", dto.getPassed()));
            Object[] rawPredicates = predicates.stream().filter(Objects::nonNull).toArray();
            return criteriaBuilder.and(Arrays.copyOf(rawPredicates, rawPredicates.length, Predicate[].class));
        };
    }
}
