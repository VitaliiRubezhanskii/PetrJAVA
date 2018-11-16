package com.petr.exception;

public class QuestionNotCorrectException extends RuntimeException {

    public QuestionNotCorrectException() {
        super("Question not correct");
    }
}
