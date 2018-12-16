package com.petr.service.question;

import com.petr.persistence.entity.Question;
import com.petr.persistence.entity.Status;
import com.petr.transport.dto.question.QuestionCreateDto;
import com.petr.transport.dto.question.QuestionFindDto;
import com.petr.transport.dto.question.QuestionOutcomeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface QuestionService {

    Question getById(Long id);

    Question create(Question question);

    Page<QuestionOutcomeDto> getAll(QuestionFindDto dto, Pageable pageable);

    Long create(QuestionCreateDto dto, Long surveyId);

    void setStatus(Long id, Status status);

    List<Long> getIdFromEntity(List<Question> questions);
}
