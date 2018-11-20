package com.petr.exception.survey;

public class SurveyNotFoundException extends RuntimeException {

    public SurveyNotFoundException() {
        super("Survey not found");
    }
}
