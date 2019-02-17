package com.petr.persistence.repository;

import com.petr.persistence.entity.Answer;
import com.petr.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{

    boolean existsByPhone(String phone);

    boolean existsByInn(String inn);

    boolean existsByEmail(String email);

    boolean existsByPassport(String passport);

    User findUserByUsername(String username);

    List<User> findUsersByParentId(User user);


}
