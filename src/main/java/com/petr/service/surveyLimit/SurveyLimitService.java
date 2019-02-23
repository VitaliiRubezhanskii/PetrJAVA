package com.petr.service.surveyLimit;

import com.petr.persistence.entity.Status;
import com.petr.persistence.entity.survey.SurveyLimit;
import com.petr.transport.dto.survetLimit.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SurveyLimitService {

    SurveyLimit getById(Long id);

    Page<SurveyLimitOutcomeDto> getAll(SurveyLimitFindDto dto, Pageable pageable);

    Long create(SurveyLimitCreateDto dto, Long surveyId);

    List<SurveyLimit> save(SurveyLimitDto dto, Long surveyId);

    void setStatus(Long id, Status status);

    List<Long> getIdFromEntity(List<SurveyLimit> surveyLimits);

    List<SurveyLimitAggregateDto> getSurveyLimits();
}
