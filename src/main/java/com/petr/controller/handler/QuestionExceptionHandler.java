package com.petr.controller.handler;

import com.petr.exception.question.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class QuestionExceptionHandler {

    @ExceptionHandler(value = QuestionExistsException.class)
    private String questionExistsException(QuestionExistsException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }


    @ExceptionHandler(value = QuestionCanNotHasAnswerException.class)
    private String questionCanNotHasAnswerException(QuestionCanNotHasAnswerException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = QuestionNotFoundException.class)
    private String questionNotFoundException(QuestionNotFoundException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = QuestionNotCorrectException.class)
    private String questionNotCorrectException(QuestionNotCorrectException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = QuestionMinMaxException.class)
    private String questionMinMaxException(QuestionMinMaxException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = QuestionDeletedException.class)
    private String questionDeletedException(QuestionDeletedException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = QuestionMinNotEqualsMaxException.class)
    private String questionMinNotEqualsMaxException(QuestionMinNotEqualsMaxException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }
}
