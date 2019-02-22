package com.petr.service.surveyResult;

import com.petr.persistence.entity.SurveyResult;
import com.petr.persistence.entity.User;

import java.util.List;

public interface SurveyResultService {

    void submit(SurveyResult survey);

    List<SurveyResult> getSurveyResultByUser(User user);

    List<SurveyResult> getSurveyResultOfAll();

//    List<LowLevelHierarchy> getSurveyResultHierarchy();
}
