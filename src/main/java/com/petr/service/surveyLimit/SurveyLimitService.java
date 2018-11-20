package com.petr.service.surveyLimit;

import com.petr.persistence.entity.survey.SurveyLimit;
import com.petr.transport.dto.survetLimit.SurveyLimitCreateDto;
import com.petr.transport.dto.survetLimit.SurveyLimitFindDto;
import com.petr.transport.dto.survetLimit.SurveyLimitOutcomeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SurveyLimitService {

    SurveyLimit getById(Long id);

    Page<SurveyLimitOutcomeDto> getAll(SurveyLimitFindDto dto, Pageable pageable);

    Long create(SurveyLimitCreateDto dto, Long surveyId);

    List<Long> getIdFromEntity(List<SurveyLimit> surveyLimits);
}
