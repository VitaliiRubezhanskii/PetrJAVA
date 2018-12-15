package com.petr.controller;

import com.petr.persistence.entity.Status;
import com.petr.persistence.entity.survey.Survey;
import com.petr.service.survey.SurveyService;
import com.petr.transport.dto.survey.SurveyCreateDto;
import com.petr.transport.dto.survey.SurveyFindDto;
import com.petr.transport.dto.survey.SurveyOutcomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/surveys")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

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

    @GetMapping
    public List<Survey> getSurveys(){
        return surveyService.findAll();
    }



    //admin
    @PostMapping("/deleted/{id}")
    public void setDeleted(@PathVariable("id") Long id) {
        surveyService.setStatus(id, Status.DELETED);
    }

    //admin
    @PostMapping("/active/{id}")
    public void setActive(@PathVariable("id") Long id) {
        surveyService.setStatus(id, Status.ACTIVE);
    }

    //admin
    @PostMapping("/unpublish/{id}")
    public void setUnpublish(@PathVariable("id") Long id) {
        surveyService.setStatus(id, Status.UNPUBLISH);
    }

    //user
    @GetMapping("/getSurveyByUser/{userId}/{location}")
    public Map<Long, Long> getSurveyById(@PathVariable("userId") Long userId, @PathVariable("location") Long location){
        return surveyService.getSurveyByUser(userId, location);
    }
}
