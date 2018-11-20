package com.petr.exception.question;

public class QuestionCanNotHasAnswerException extends RuntimeException {

    public QuestionCanNotHasAnswerException() {
        super("Question can not has answer");
    }
}
