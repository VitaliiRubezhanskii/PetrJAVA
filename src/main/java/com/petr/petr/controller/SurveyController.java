package com.petr.petr.controller;

import com.petr.petr.service.survey.SurveyService;
import com.petr.petr.transport.dto.survey.SurveyCreateDto;
import com.petr.petr.transport.dto.survey.SurveyFindDto;
import com.petr.petr.transport.dto.survey.SurveyOutcomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/surveys")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    //admin
    @PutMapping
    public Long create(@RequestBody @Valid SurveyCreateDto dto) {
        return surveyService.create(dto);
    }

    //admin
    @GetMapping
    public Page<SurveyOutcomeDto> getSurveys(SurveyFindDto dto,
                                             @PageableDefault(size = 5) Pageable pageable) {
        return surveyService.getAll(dto, pageable);
    }

}
