package com.petr.transport.mapper;

import com.petr.persistence.entity.Question;
import com.petr.service.answer.AnswerService;
import com.petr.service.question.QuestionService;
import com.petr.transport.dto.question.QuestionCreateDto;
import com.petr.transport.dto.question.QuestionOutcomeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class QuestionMapper {

    @Autowired
    AnswerService answerService;

    public abstract Question toEntity(QuestionCreateDto dto);

    @Mappings({
            @Mapping(target = "survey", expression = "java(question.getSurvey().getId())"),
            @Mapping(target = "answers", expression = "java(answerService.getIdFromEntity(question.getAnswers()))")
    })
    public abstract QuestionOutcomeDto toDto(Question question);
}
