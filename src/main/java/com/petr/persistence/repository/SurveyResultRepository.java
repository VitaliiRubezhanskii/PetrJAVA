package com.petr.persistence.repository;

import com.petr.persistence.entity.SurveyResult;
import com.petr.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SurveyResultRepository extends JpaRepository<SurveyResult, Long> {

    List<SurveyResult> getSurveyResultByUser(User user);

}
