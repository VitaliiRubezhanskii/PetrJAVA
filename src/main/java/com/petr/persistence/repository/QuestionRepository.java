package com.petr.persistence.repository;

import com.petr.persistence.entity.Answer;
import com.petr.persistence.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {

    boolean existsByTextAndSurveyId(String text, Long surveyId);

    @Query("select distinct q from Question q inner join q.answers a where a in :answers")
    List<Question> findDistinctQuestionsByAnswers(@Param("answers") List<Answer> answers);
}
