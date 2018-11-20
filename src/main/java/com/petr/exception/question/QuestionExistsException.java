package com.petr.exception.question;

public class QuestionExistsException extends RuntimeException {

    public QuestionExistsException() {
        super("Question name exists");
    }
}
