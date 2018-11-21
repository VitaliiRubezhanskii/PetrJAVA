package com.petr.controller;

import com.petr.persistence.entity.Status;
import com.petr.service.surveyLimit.SurveyLimitService;
import com.petr.transport.dto.survetLimit.SurveyLimitCreateDto;
import com.petr.transport.dto.survetLimit.SurveyLimitFindDto;
import com.petr.transport.dto.survetLimit.SurveyLimitOutcomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/surveyLimits")
@RequiredArgsConstructor
public class SurveyLimitController {

    private final SurveyLimitService surveyLimitService;

    //admin
    @PutMapping("/{surveyId}")
    public Long create(@RequestBody @Valid SurveyLimitCreateDto dto, @PathVariable("surveyId") Long surveyId) {
        return surveyLimitService.create(dto, surveyId);
    }

    //admin
    @GetMapping
    public Page<SurveyLimitOutcomeDto> getSurveys(SurveyLimitFindDto dto,
                                                  @PageableDefault(size = 5) Pageable pageable) {
        return surveyLimitService.getAll(dto, pageable);
    }

    //admin
    @PostMapping("/deleted/{id}")
    public void setDeleted(@PathVariable("id") Long id) {
        surveyLimitService.setStatus(id, Status.DELETED);
    }

    //admin
    @PostMapping("/active/{id}")
    public void setActive(@PathVariable("id") Long id) {
        surveyLimitService.setStatus(id, Status.ACTIVE);
    }

    //admin
    @PostMapping("/unpublish/{id}")
    public void setUnpublish(@PathVariable("id") Long id) {
        surveyLimitService.setStatus(id, Status.UNPUBLISH);
    }

}
