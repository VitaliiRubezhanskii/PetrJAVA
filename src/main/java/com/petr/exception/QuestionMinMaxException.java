package com.petr.exception;

public class QuestionMinMaxException extends RuntimeException {

    public QuestionMinMaxException() {
        super("Question min > max");
    }
}
