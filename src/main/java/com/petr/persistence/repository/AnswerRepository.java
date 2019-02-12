package com.petr.persistence.repository;

import com.petr.persistence.entity.Answer;
import com.petr.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long>, JpaSpecificationExecutor<Answer> {

   boolean existsByValueAndQuestionId(String value, Long id);

   @Query(value = "select answer_id from user_answer where user_answer.user_id = :userId", nativeQuery = true)
   List<BigInteger> findAllByUser(@Param("userId") Long userId);
}
