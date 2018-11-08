package com.petr.petr.exception;

public class QuestionExistsException extends RuntimeException {

    public QuestionExistsException() {
        super("Question name exists");
    }
}
