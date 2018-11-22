package com.petr.persistence.repository;

import com.petr.persistence.entity.Status;
import com.petr.persistence.entity.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long>, JpaSpecificationExecutor<Survey> {

    boolean existsByName(String name);

    List<Survey> findAllByStatus(Status status);
}
