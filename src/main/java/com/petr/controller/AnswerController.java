package com.petr.controller;

import com.petr.persistence.entity.Answer;
import com.petr.persistence.entity.Question;
import com.petr.persistence.entity.Status;
import com.petr.persistence.entity.User;
import com.petr.persistence.repository.AnswerRepository;
import com.petr.service.answer.AnswerService;
import com.petr.service.question.QuestionService;
import com.petr.service.user.UserService;
import com.petr.transport.dto.answer.AnswerCreateDto;
import com.petr.transport.dto.answer.AnswerFindDto;
import com.petr.transport.dto.answer.AnswerOutcomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;
    private final AnswerRepository answerRepository;
    private final UserService userService;

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

    @PostMapping(value = "/giveAnswer/{answerId}/user/{userId}")
    public void answerQuestion(@PathVariable("answerId") Long answerId,
                               @PathVariable("userId") Long userId){
       answerService.save(answerId, userId);
    }

    @GetMapping
    public Page<AnswerOutcomeDto> getQuestions(AnswerFindDto dto,
                                               @PageableDefault(size = 5) Pageable pageable) {
        return answerService.getAll(dto, pageable);
    }

    @GetMapping(value = "/user/{userId}")
    public List<Answer> findAnswersByUser(@PathVariable("userId") Long userId){
        return answerService.findAnswersByUsers(userService.getById(userId));
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
