package com.petr.service.question;

import com.petr.exception.question.QuestionExistsException;
import com.petr.exception.question.QuestionMinMaxException;
import com.petr.exception.question.QuestionMinNotEqualsMaxException;
import com.petr.exception.question.QuestionNotFoundException;
import com.petr.exception.survey.SurveyDeletedException;
import com.petr.exception.survey.SurveyHasNotSurveyLimitException;
import com.petr.persistence.entity.Question;
import com.petr.persistence.entity.QuestionType;
import com.petr.persistence.entity.Status;
import com.petr.persistence.entity.survey.Survey;
import com.petr.persistence.repository.QuestionRepository;
import com.petr.service.survey.SurveyService;
import com.petr.transport.dto.question.QuestionCreateDto;
import com.petr.transport.dto.question.QuestionFindDto;
import com.petr.transport.dto.question.QuestionOutcomeDto;
import com.petr.transport.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuestionServiceImpl extends QuestionSearchSpecification implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    private SurveyService surveyService;

    @Autowired
    public void setSurveyService(SurveyService surveyService) {
        this.surveyService = surveyService;
    }


    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public Question getById(Long id){
        return questionRepository.findById(id).orElseThrow(QuestionNotFoundException::new);
    }

    @Override
    public Page<QuestionOutcomeDto> getAll(QuestionFindDto dto, Pageable pageable) {
        Page<Question> result = questionRepository.findAll(
                questionFilter(dto),
                pageable
        );
        return result.map(questionMapper::toDto);
    }

    @Override
    public Long create(QuestionCreateDto dto, Long surveyId) {
        validateQuestion(dto, surveyId);
        Survey survey = surveyService.getById(surveyId);
        Question question = questionMapper.toEntity(dto);
        question.setSurvey(survey);
        return questionRepository.save(question).getId();
    }

    @Override
    public void setStatus(Long id, Status status){
        getById(id).setStatus(status);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    private void validateQuestion(QuestionCreateDto dto, Long surveyId) {
        if (surveyService.getById(surveyId).getStatus().equals(Status.DELETED)){
            throw new SurveyDeletedException();
        }
//        if (surveyService.getById(surveyId).getSurveyLimits().isEmpty()){
//            throw new SurveyHasNotSurveyLimitException();
//        }
        if (questionRepository.existsByTextAndSurveyId(dto.getText(), surveyId)) {
            throw new QuestionExistsException();
        }
//        if (dto.getMin()>dto.getMax()){
//            throw  new QuestionMinMaxException();
//        }
//        if (dto.getType().equals(QuestionType.LONG_ANSWER)){
//            if (dto.getMin()>1||dto.getMax()>1){
//                throw new QuestionMinNotEqualsMaxException();
//            }
//        }
    }

    @Override
    public List<Long> getIdFromEntity(List<Question> questions) {
        if (questions == null) {
            return null;
        }
        List<Long> userIds = new ArrayList<>();
        for (Question question : questions) {
            userIds.add(question.getId());
        }
        return userIds;
    }

    @Override
    public Question create(Question question) {
        return questionRepository.save(question);
    }
}
