package com.petr.controller;

import com.petr.persistence.entity.Status;
import com.petr.persistence.entity.SurveyResult;
import com.petr.persistence.entity.User;
import com.petr.persistence.entity.survey.Survey;
import com.petr.service.survey.SurveyService;
import com.petr.service.surveyResult.SurveyResultService;
import com.petr.service.user.UserService;
import com.petr.transport.dto.survey.SurveyCreateDto;
import com.petr.transport.dto.survey.SurveyFindDto;
import com.petr.transport.dto.survey.SurveyOutcomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/surveys")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;
    private final SurveyResultService surveyResultService;
    private final UserService userService;

    //admin
    @PostMapping(value = "/new")
    public Long create(@RequestBody @Valid SurveyCreateDto dto) {
        return surveyService.create(dto);
    }

    //admin
//    @GetMapping
//    public Page<SurveyOutcomeDto> getSurveys(SurveyFindDto dto,
//                                             @PageableDefault(size = 5) Pageable pageable) {
//        return surveyService.getAll(dto, pageable);
//    }

//    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Survey create(@RequestBody Survey survey) {
//        return surveyService.save(survey);
//    }


    @GetMapping
    public List<Survey> getSurveys(){
        return surveyService.findAll();
    }

    @PostMapping(value = "/submit/survey/{surveyId}/user/{userId}")
    public void submitSurvey(@PathVariable("surveyId") Long surveyId, @PathVariable("userId") Long userId){
        SurveyResult surveyResult = new SurveyResult();
        Survey survey = surveyService.getById(surveyId);
        User user = userService.getById(userId);
        surveyResult.setSurvey(survey);
        surveyResult.setDate(new java.util.Date());
        surveyResult.setUser(user);
        surveyResult.setBonus(10);
        surveyResultService.submit(surveyResult);
    }

    //admin
    @PostMapping(value = "/survey/{id}/status/{surveyStatus}")
    public void setSurveyStatus(@PathVariable("id") Long id, @PathVariable("surveyStatus") Status surveyStatus ) {
        surveyService.setStatus(id, surveyStatus);
    }


    @GetMapping(value = "/bylimits/{userId}")
    public List<Survey> getSurveysMatchingUserLimits(@PathVariable("userId") Long userId){
        return surveyService.findSurveysByUserLimit(userId);
    }

//    //admin
//    @PostMapping(value = "/active/{id}")
//    public void setActive(@PathVariable("id") Long id) {
//        surveyService.setStatus(id, Status.ACTIVE);
//    }
//
//    //admin
//    @PostMapping(value = "/unpublish/{id}")
//    public void setUnpublish(@PathVariable("id") Long id) {
//        surveyService.setStatus(id, Status.UNPUBLISH);
//    }

    //user
    @GetMapping(value = "/getSurveyByUser/{userId}/{location}")
    public Map<Long, Long> getSurveyByUserId(@PathVariable("userId") Long userId, @PathVariable("location") Long location){
        return surveyService.getSurveyByUser(userId, location);
    }

    @GetMapping(value = "/survey/{surveyId}")
    public Survey getSurveyById(@PathVariable("surveyId") Long surveyId){
        return surveyService.getById(surveyId);
    }



}
