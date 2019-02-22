package com.petr.persistence.repository;

import com.petr.persistence.entity.Question;
import com.petr.persistence.entity.Status;
import com.petr.persistence.entity.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long>, JpaSpecificationExecutor<Survey> {

    boolean existsByName(String name);

    List<Survey> findAllByStatus(Status status);

    @Query("select s from Survey s inner join  s.questions q where q in :questions")
    List<Survey> findDistinctSurveysByQuestions(@Param("questions") List<Question> questions);
}
