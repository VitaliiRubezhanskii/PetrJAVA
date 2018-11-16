package com.petr.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class AbstractSearchSpecification<T> {

    protected Predicate toEqualsPredicate(Root<T> root, CriteriaBuilder criteriaBuilder, String param, Object paramValue) {
        return paramValue != null ? criteriaBuilder.equal(root.get(param), paramValue) : null;

    }

    protected Predicate toEqualsPredicateIds(Root<T> root, String param, List<Long> paramValue) {
        return paramValue != null ? root.join(param).get("id").in(paramValue) : null;
    }

    protected Predicate toLikePredicate(Root<T> root, CriteriaBuilder criteriaBuilder, String param, Object paramValue) {
        return paramValue != null ? criteriaBuilder.like(root.get(param), "%" + paramValue + "%") : null;
    }

    protected Predicate toEqualsPredicateId(Root<T> root, CriteriaBuilder criteriaBuilder, String param, Long paramValue) {
        return paramValue != null ? criteriaBuilder.equal(root.get(param).get("id"), paramValue) : null;
    }

    protected Predicate toPredicateBetween(Root<T> root, CriteriaBuilder criteriaBuilder, String param,
                                           Long paramValueFrom, Long paramValueTo) {
        if (paramValueFrom == null && paramValueTo == null) {
            return null;
        }
        if (paramValueFrom == null) {
            paramValueFrom = 0L;
        } else {
            paramValueTo = Long.MAX_VALUE;
        }
        return criteriaBuilder.between(root.get(param), paramValueFrom, paramValueTo);
    }
}

