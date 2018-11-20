package com.petr.transport.mapper;

import com.petr.persistence.entity.survey.SurveyLimit;
import com.petr.service.survey.SurveyService;
import com.petr.transport.dto.survetLimit.SurveyLimitCreateDto;
import com.petr.transport.dto.survetLimit.SurveyLimitOutcomeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SurveyLimitMapper {

    public abstract SurveyLimit toEntity(SurveyLimitCreateDto dto);

    @Mapping(target = "survey", expression = "java(surveyLimit.getSurvey().getId())")
    public abstract SurveyLimitOutcomeDto toDto(SurveyLimit surveyLimit);
}
