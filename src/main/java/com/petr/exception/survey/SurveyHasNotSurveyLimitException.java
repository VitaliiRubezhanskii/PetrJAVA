package com.petr.exception.survey;

public class SurveyHasNotSurveyLimitException extends RuntimeException {

    public SurveyHasNotSurveyLimitException() {
        super("Survey has not surveyLimit");
    }
}
