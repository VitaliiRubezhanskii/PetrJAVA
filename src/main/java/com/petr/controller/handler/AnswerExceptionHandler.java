package com.petr.controller.handler;

import com.petr.exception.answer.AnswerExistsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AnswerExceptionHandler {

    @ExceptionHandler(value = AnswerExistsException.class)
    private String answerExistsException(AnswerExistsException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }
}
