package com.petr.exception.answer;

public class AnswerExistsException extends RuntimeException {

    public AnswerExistsException() {
        super("Answer exists");
    }
}
