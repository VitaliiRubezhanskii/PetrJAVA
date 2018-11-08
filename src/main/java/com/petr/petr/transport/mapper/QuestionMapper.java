package com.petr.petr.transport.mapper;

import com.petr.petr.persistence.entity.Question;
import com.petr.petr.transport.dto.question.QuestionCreateDto;
import com.petr.petr.transport.dto.question.QuestionOutcomeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class QuestionMapper {

    public abstract Question toEntity(QuestionCreateDto dto);

    @Mapping(target = "survey", expression = "java(question.getSurvey().getId())")
    public abstract QuestionOutcomeDto toDto(Question question);
}
