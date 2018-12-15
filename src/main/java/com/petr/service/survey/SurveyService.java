package com.petr.service.survey;

import com.petr.persistence.entity.Status;
import com.petr.persistence.entity.survey.Survey;
import com.petr.transport.dto.survey.SurveyCreateDto;
import com.petr.transport.dto.survey.SurveyFindDto;
import com.petr.transport.dto.survey.SurveyOutcomeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
public interface SurveyService {
    Survey getById(Long id);

    Page<SurveyOutcomeDto> getAll(SurveyFindDto dto, Pageable pageable);

    Long create(SurveyCreateDto dto);

    Long save(Survey survey);

    void setStatus(Long id, Status status);

    Map<Long, Long> getSurveyByUser(Long userId, long location);
    List<Survey> findAll();
}
