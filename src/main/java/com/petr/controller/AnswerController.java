package com.petr.controller;

import com.petr.persistence.entity.Answer;
import com.petr.persistence.entity.Question;
import com.petr.persistence.entity.Status;
import com.petr.persistence.repository.AnswerRepository;
import com.petr.service.answer.AnswerService;
import com.petr.service.question.QuestionService;
import com.petr.transport.dto.answer.AnswerCreateDto;
import com.petr.transport.dto.answer.AnswerFindDto;
import com.petr.transport.dto.answer.AnswerOutcomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;
    private final AnswerRepository answerRepository;

//    @PutMapping("/{questionId}")
//    public Long create(@RequestBody @Valid AnswerCreateDto dto,
//                       @PathVariable("questionId") Long questionId) {
//        return answerService.create(dto, questionId);
//    }

    @PutMapping(value = "/{questionId}")
    public Long create(@RequestBody Answer answer,@PathVariable("questionId") Long questionId ){
        Question question = questionService.getById(questionId);
        answer.setQuestion(question);
        return answerRepository.save(answer).getId();
    }


    @GetMapping
    public Page<AnswerOutcomeDto> getQuestions(AnswerFindDto dto,
                                               @PageableDefault(size = 5) Pageable pageable) {
        return answerService.getAll(dto, pageable);
    }

    //admin
    @PostMapping("/deleted/{id}")
    public void setDeleted(@PathVariable("id") Long id) {
        answerService.setStatus(id, Status.DELETED);
    }

    //admin
    @PostMapping("/active/{id}")
    public void setActive(@PathVariable("id") Long id) {
        answerService.setStatus(id, Status.ACTIVE);
    }

    //admin
    @PostMapping("/unpublish/{id}")
    public void setUnpublish(@PathVariable("id") Long id) {
        answerService.setStatus(id, Status.UNPUBLISH);
    }
}
