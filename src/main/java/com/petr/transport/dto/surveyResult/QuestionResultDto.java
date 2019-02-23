package com.petr.transport.dto.surveyResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResultDto {
    private String userFullName; //questionText;
    private String surveyName; //responseOption;
    private String surveySubmissionDate;//responseDate;


}
