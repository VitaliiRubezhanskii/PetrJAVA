package com.petr.exception.user;

public class PassportSecondPageException extends RuntimeException {

    public PassportSecondPageException() {
        super("User has passportSecondPage");
    }
}
