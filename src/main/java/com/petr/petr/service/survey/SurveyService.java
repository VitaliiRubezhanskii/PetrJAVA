package com.petr.petr.service.survey;

import com.petr.petr.transport.dto.SurveyCreateDto;
import com.petr.petr.transport.dto.SurveyFindDto;
import com.petr.petr.transport.dto.SurveyOutcomeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SurveyService {
    Page<SurveyOutcomeDto> getAll(SurveyFindDto dto, Pageable pageable);

    Long create(SurveyCreateDto dto);
}
