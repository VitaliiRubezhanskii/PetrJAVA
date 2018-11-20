package com.petr.persistence.repository;

import com.petr.persistence.entity.Answer;
import com.petr.persistence.entity.survey.SurveyLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SurveyLimitRepository extends JpaRepository<SurveyLimit, Long>, JpaSpecificationExecutor<SurveyLimit> {
}
