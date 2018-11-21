package com.petr.exception.answer;

public class AnswerNotFoundException extends RuntimeException {

    public AnswerNotFoundException() {
        super("Answer not found");
    }
}
