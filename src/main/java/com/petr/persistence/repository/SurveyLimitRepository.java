package com.petr.persistence.repository;

import com.petr.persistence.entity.Gender;
import com.petr.persistence.entity.survey.Survey;
import com.petr.persistence.entity.survey.SurveyLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SurveyLimitRepository extends JpaRepository<SurveyLimit, Long>, JpaSpecificationExecutor<SurveyLimit> {

    @Query("select s from SurveyLimit s where  s.gender=:gender and s.location = :location")
    List<SurveyLimit> findLimitByLocationAnd( @Param("location") String location, @Param("gender") Gender gender );

    void deleteAllBySurvey(Survey survey);

    List<SurveyLimit> findSurveyLimitsBySurvey(Survey survey);

}
