package com.petr.service.surveyResult;

import com.petr.persistence.entity.SurveyResult;
import com.petr.persistence.entity.User;
import com.petr.transport.dto.surveyResult.SurveyUserResultDto;
import com.petr.transport.dto.user.UserOutcomeDto;

import java.util.List;

public interface SurveyResultService {

    void submit(SurveyResult survey);

    List<SurveyResult> getSurveyResultByUser(User user);

    List<UserOutcomeDto> getSurveyResults();

    List<SurveyUserResultDto> getAllSurveyUserResults();
}
