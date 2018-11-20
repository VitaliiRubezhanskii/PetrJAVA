package com.petr.exception.survey;

public class SurveyExistsException extends RuntimeException {

    public SurveyExistsException() {
        super("Survey name exists");
    }
}
