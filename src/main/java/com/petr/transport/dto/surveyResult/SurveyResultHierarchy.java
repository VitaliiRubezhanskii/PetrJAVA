package com.petr.transport.dto.surveyResult;

import com.petr.persistence.entity.SurveyResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResultHierarchy {

    private SurveyResult surveyResult;
    private List<SurveyResult> embeddedResults;
}

