package com.petr.service.bank;

import com.petr.persistence.entity.Bank;
import com.petr.service.AbstractSearchSpecification;
import com.petr.transport.dto.bank.BankFindDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class BankSearchSpecification extends AbstractSearchSpecification<Bank> {
    Specification<Bank> bankFilter(BankFindDto dto) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "id", dto.getId()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "name", dto.getName()));
            predicates.add(toEqualsPredicateIds(root, "users", dto.getUsers()));
            predicates.add(toPredicateBetween(root, criteriaBuilder, "date", dto.getStartDate(), dto.getFinishDate()));
            Object[] rawPredicates = predicates.stream().filter(Objects::nonNull).toArray();
            return criteriaBuilder.and(Arrays.copyOf(rawPredicates, rawPredicates.length, Predicate[].class));
        };
    }
}
