package com.petr.exception.question;

public class QuestionNotCorrectException extends RuntimeException {

    public QuestionNotCorrectException() {
        super("Question not correct");
    }
}
