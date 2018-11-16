package com.petr.service.survey;

import com.petr.persistence.entity.Survey;
import com.petr.transport.dto.survey.SurveyCreateDto;
import com.petr.transport.dto.survey.SurveyFindDto;
import com.petr.transport.dto.survey.SurveyOutcomeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SurveyService {
    Survey getById(Long id);

    Page<SurveyOutcomeDto> getAll(SurveyFindDto dto, Pageable pageable);

    Long create(SurveyCreateDto dto);
}