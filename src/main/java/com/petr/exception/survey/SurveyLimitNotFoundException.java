package com.petr.exception.survey;

public class SurveyLimitNotFoundException extends RuntimeException {

    public SurveyLimitNotFoundException() {
        super("SurveyLimit not found");
    }
}
