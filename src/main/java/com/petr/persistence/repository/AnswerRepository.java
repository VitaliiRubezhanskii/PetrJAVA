package com.petr.persistence.repository;

import com.petr.persistence.entity.Answer;
import com.petr.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long>, JpaSpecificationExecutor<Answer> {

   boolean existsByValueAndQuestionId(String value, Long id);

   @Query(value = "select answer from Answer answer inner join answer.users u where u in (:users)")
   List<Answer> findAllByUser(@Param("users") List<User> user);

   @Modifying
   @Query(value = "delete from user_answer where user_answer.answer_id = :id", nativeQuery = true)
   int deleteResponse(@Param("id") Long id);
}
