package com.petr.transport.mapper;

import com.petr.persistence.entity.survey.Survey;
import com.petr.service.question.QuestionService;
import com.petr.service.surveyLimit.SurveyLimitService;
import com.petr.transport.dto.survey.SurveyCreateDto;
import com.petr.transport.dto.survey.SurveyOutcomeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SurveyMapper {

    @Autowired
    QuestionService questionService;

    @Autowired
    SurveyLimitService surveyLimitService;

    public abstract Survey toEntity(SurveyCreateDto dto);

    @Mapping(target = "questions", expression = "java(questionService.getIdFromEntity(survey.getQuestions()))")
    @Mapping(target = "surveyLimits", expression = "java(surveyLimitService.getIdFromEntity(survey.getSurveyLimits()))")
    public abstract SurveyOutcomeDto toDto(Survey survey);
}
