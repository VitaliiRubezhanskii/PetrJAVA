package com.petr.petr.service;

import com.petr.petr.transport.dto.QuestionCreateDto;
import com.petr.petr.transport.dto.SurveyCreateDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface QuestionServiceImpl {
//    Page<SurveyOutcomeDto> getAll(SurveyFindDto dto, Pageable pageable);

    Long create(QuestionCreateDto dto);
}
