package com.petr.exception.question;

public class QuestionDeletedException extends RuntimeException {

    public QuestionDeletedException() {
        super("Question deleted");
    }
}
