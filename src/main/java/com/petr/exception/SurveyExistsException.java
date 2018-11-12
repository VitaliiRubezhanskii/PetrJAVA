package com.petr.exception;

public class SurveyExistsException extends RuntimeException {

    public SurveyExistsException() {
        super("Survey name exists");
    }
}
