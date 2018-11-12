package com.petr.transport.mapper;

import com.petr.persistence.entity.Answer;
import com.petr.persistence.entity.Question;
import com.petr.transport.dto.answer.AnswerCreateDto;
import com.petr.transport.dto.answer.AnswerOutcomeDto;
import com.petr.transport.dto.question.QuestionCreateDto;
import com.petr.transport.dto.question.QuestionOutcomeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AnswerMapper {

    public abstract Answer toEntity(AnswerCreateDto dto);

    @Mapping(target = "question", expression = "java(answer.getQuestion().getId())")
    public abstract AnswerOutcomeDto toDto(Answer answer);
}
