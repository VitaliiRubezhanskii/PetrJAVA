package com.petr.controller.handler;

import com.petr.exception.survey.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SurveyExceptionHandler {

    @ExceptionHandler(value = SurveyExistsException.class)
    private String surveyExistsException(SurveyExistsException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = SurveyNotFoundException.class)
    private String surveyNotFoundException(SurveyNotFoundException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = SurveyLimitNotFoundException.class)
    private String surveyLimitNotFoundException(SurveyLimitNotFoundException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = SurveyDeletedException.class)
    private String surveyDeletedException(SurveyDeletedException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = SurveyLimitMinMaxException.class)
    private String surveyLimitMinMaxException(SurveyLimitMinMaxException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = SurveyLimitCountException.class)
    private String SurveyLimitCountException(SurveyLimitCountException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }
}
