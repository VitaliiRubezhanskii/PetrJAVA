package com.petr.petr.service;

import com.petr.petr.persistence.entity.Question;
import com.petr.petr.transport.dto.question.QuestionCreateDto;
import com.petr.petr.transport.dto.question.QuestionFindDto;
import com.petr.petr.transport.dto.question.QuestionOutcomeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface QuestionService {

    Page<QuestionOutcomeDto> getAll(QuestionFindDto dto, Pageable pageable);

    Long create(QuestionCreateDto dto, Long surveyId);

    List<Long> getIdFromEntity(List<Question> questions);
}
