package com.petr.transport.dto.surveyResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyUserResultDto {

    private String userFullName;
    private String surveyName;
    private String surveySubmissionDate;
    private List<QuestionResultDto> questionResultDtos;


}
