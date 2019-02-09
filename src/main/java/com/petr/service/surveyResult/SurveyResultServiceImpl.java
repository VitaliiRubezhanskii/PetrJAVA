package com.petr.service.surveyResult;

import com.petr.persistence.entity.SurveyResult;
import com.petr.persistence.entity.User;
import com.petr.persistence.entity.survey.Survey;
import com.petr.persistence.repository.SurveyResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jnlp.UnavailableServiceException;
import java.util.List;

@Service
public class SurveyResultServiceImpl implements SurveyResultService {

    private final SurveyResultRepository surveyResultRepository;

    @Autowired
    public SurveyResultServiceImpl(SurveyResultRepository surveyResultRepository) {
        this.surveyResultRepository = surveyResultRepository;
    }

    @Override
    public void submit(SurveyResult survey) {
        surveyResultRepository.save(survey);
    }

    @Override
    public List<SurveyResult> getSurveyResultByUser(User user) {
        return surveyResultRepository.getSurveyResultByUser(user);
    }

    @Override
    public List<SurveyResult> getSurveyResultOfAll() {
        return surveyResultRepository.findAll();
    }
}
