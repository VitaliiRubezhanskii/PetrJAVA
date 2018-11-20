package com.petr.exception.question;

public class QuestionMinMaxException extends RuntimeException {

    public QuestionMinMaxException() {
        super("Question min > max");
    }
}
