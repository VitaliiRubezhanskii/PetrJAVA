package com.petr.exception;

public class AnswerExistsException extends RuntimeException {

    public AnswerExistsException() {
        super("Answer exists");
    }
}
