package com.petr.exception;

public class PassportSecondPageException extends RuntimeException {

    public PassportSecondPageException() {
        super("User has passportSecondPage");
    }
}
