package com.petr.transport.dto.surveyResult;

import com.petr.transport.dto.survey.SurveyOutcomeDto;
import com.petr.transport.dto.user.UserOutcomeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResultDto {

    private UserOutcomeDto userOutcomeDto;
//    private SurveyOutcomeDto surveyResultDto;
    private Long ownSurveysCount;
    private Integer totalAccountedScoring;



}
