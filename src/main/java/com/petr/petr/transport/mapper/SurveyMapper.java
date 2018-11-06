package com.petr.petr.transport.mapper;

import com.petr.petr.persistence.entity.Survey;
import com.petr.petr.transport.dto.SurveyCreateDto;
import com.petr.petr.transport.dto.SurveyOutcomeDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SurveyMapper {

    Survey toEntity(SurveyCreateDto dto);

    SurveyOutcomeDto toDto(Survey survey);
}
