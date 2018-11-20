package com.petr.exception.survey;

public class SurveyLimitMinMaxException extends RuntimeException {

    public SurveyLimitMinMaxException() {
        super("SurveyLimit min age > max age ");
    }
}
