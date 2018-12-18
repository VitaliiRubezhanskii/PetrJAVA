package com.petr.controller;

import com.petr.persistence.entity.Question;
import com.petr.persistence.entity.Status;
import com.petr.service.question.QuestionService;
import com.petr.transport.dto.question.QuestionCreateDto;
import com.petr.transport.dto.question.QuestionFindDto;
import com.petr.transport.dto.question.QuestionOutcomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
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

    @PostMapping(value = "/new")
    public void create(@RequestBody Question question){
         questionService.create(question);
    }


    @GetMapping
    public Page<QuestionOutcomeDto> getQuestions(QuestionFindDto dto,
                                                 @PageableDefault(size = 5) Pageable pageable) {
        return questionService.getAll(dto, pageable);
    }

    //admin
    @PostMapping("/deleted/{id}")
    public void setDeleted(@PathVariable("id") Long id) {
        questionService.setStatus(id, Status.DELETED);
    }

    //admin
    @PostMapping("/active/{id}")
    public void setActive(@PathVariable("id") Long id) {
        questionService.setStatus(id, Status.ACTIVE);
    }

    //admin
    @PostMapping("/unpublish/{id}")
    public void setUnpublish(@PathVariable("id") Long id) {
        questionService.setStatus(id, Status.UNPUBLISH);
    }

    @DeleteMapping(value = "/delete/question/{id}")
    public void deleteQuestion(@PathVariable("id") Long id){
        questionService.deleteQuestion(id);
    }

}
