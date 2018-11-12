package com.petr.exception;

public class QuestionCanNotHasAnswerException extends RuntimeException {

    public QuestionCanNotHasAnswerException() {
        super("Question can not has answer");
    }
}
