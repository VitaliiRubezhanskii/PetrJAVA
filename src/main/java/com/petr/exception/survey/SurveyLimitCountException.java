package com.petr.exception.survey;

public class SurveyLimitCountException extends RuntimeException {

    public SurveyLimitCountException() {
        super("Survey count < surveyLimit count");
    }
}
