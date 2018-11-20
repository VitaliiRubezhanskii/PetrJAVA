package com.petr.exception.survey;

public class SurveyDeletedException extends RuntimeException {

    public SurveyDeletedException() {
        super("Survey deleted");
    }
}
