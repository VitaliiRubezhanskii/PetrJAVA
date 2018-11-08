package com.petr.petr.controller;

import com.petr.petr.service.QuestionService;
import com.petr.petr.transport.dto.question.QuestionCreateDto;
import com.petr.petr.transport.dto.question.QuestionFindDto;
import com.petr.petr.transport.dto.question.QuestionOutcomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PutMapping("/{surveyId}")
    public Long create(@RequestBody @Valid QuestionCreateDto dto,
                       @PathVariable("surveyId") Long surveyId) {
        return questionService.create(dto, surveyId);
    }

    @GetMapping
    public Page<QuestionOutcomeDto> getQuestions(QuestionFindDto dto,
                                                 @PageableDefault(size = 5) Pageable pageable) {
        return questionService.getAll(dto, pageable);
    }
}
