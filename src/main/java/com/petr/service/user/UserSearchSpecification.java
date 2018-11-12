package com.petr.service.user;

import com.petr.persistence.entity.User;
import com.petr.transport.dto.user.UserFindDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public interface UserSearchSpecification {
    static Specification<User> userFilter(UserFindDto dto) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "id", dto.getId()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "verify", dto.isVerify()));
            predicates.add(toEqualsPredicate(root, criteriaBuilder, "deleted", dto.isDeleted()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "name", dto.getName()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "surname", dto.getSurname()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "patronymic", dto.getPatronymic()));
            predicates.add(toPredicateBetween(root, criteriaBuilder, "birthDate", dto.getStartBirthDate(), dto.getFinishBirthDate()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "phone", dto.getPhone()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "email", dto.getEmail()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "inn", dto.getInn()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "passport", dto.getPassport()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "issuedBy", dto.getIssuedBy()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "issuedWhen", dto.getIssuedWhen()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "passwordFirstPage", dto.getPasswordFirstPage()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "passwordSecondPage", dto.getPasswordSecondPage()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "passwordLastPage", dto.getPasswordLastPage()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "photoInn", dto.getPhotoInn()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "photo", dto.getPhone()));
            predicates.add(toEqualsPredicateId(root, criteriaBuilder, "bank", dto.getBank()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "phone", dto.getPhone()));
            predicates.add(toLikePredicate(root, criteriaBuilder, "card", dto.getCard()));
            predicates.add(toEqualsPredicateId(root, criteriaBuilder, "parentId", dto.getParentId()));
            Object[] rawPredicates = predicates.stream().filter(Objects::nonNull).toArray();
            return criteriaBuilder.and(Arrays.copyOf(rawPredicates, rawPredicates.length, Predicate[].class));
        };
    }

    static Predicate toEqualsPredicate(Root<User> root, CriteriaBuilder criteriaBuilder, String param, Object paramValue) {
        return paramValue != null ? criteriaBuilder.equal(root.get(param), paramValue) : null;
    }

    static Predicate toLikePredicate(Root<User> root, CriteriaBuilder criteriaBuilder, String param, Object paramValue) {
        return paramValue != null ? criteriaBuilder.like(root.get(param), "%" + paramValue + "%") : null;
    }

    static Predicate toEqualsPredicateId(Root<User> root, CriteriaBuilder criteriaBuilder, String param, Long paramValue) {
        return paramValue != null ? criteriaBuilder.equal(root.get(param).get("id"), paramValue) : null;
    }

     static Predicate toPredicateBetween(Root<User> root, CriteriaBuilder criteriaBuilder, String param,
                                                Long paramValueFrom, Long paramValueTo) {
        return (paramValueFrom != null && paramValueTo != null) ? criteriaBuilder.between(root.get(param), paramValueFrom, paramValueTo) : null;
    }
}
