package com.petr.exception.question;

public class QuestionMinNotEqualsMaxException extends RuntimeException {

    public QuestionMinNotEqualsMaxException() {
        super("Question type text must min = max");
    }
}
