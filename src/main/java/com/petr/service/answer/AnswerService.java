package com.petr.service.answer;

import com.petr.persistence.entity.Answer;
import com.petr.transport.dto.answer.AnswerCreateDto;
import com.petr.transport.dto.answer.AnswerFindDto;
import com.petr.transport.dto.answer.AnswerOutcomeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AnswerService {
    Page<AnswerOutcomeDto> getAll(AnswerFindDto dto, Pageable pageable);

    Long create(AnswerCreateDto dto, Long questionId);

    List<Long> getIdFromEntity(List<Answer> answers);
}
