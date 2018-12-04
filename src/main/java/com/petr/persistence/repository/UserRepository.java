package com.petr.persistence.repository;

import com.petr.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    boolean existsByPhone(String phone);

    boolean existsByInn(String inn);

    boolean existsByEmail(String email);

    boolean existsByPassport(String passport);

    User findUserByEmail(String username);
}
