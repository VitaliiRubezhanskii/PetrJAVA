package com.petr.exception;

public class PassportLastPageException extends RuntimeException {

    public PassportLastPageException() {
        super("User has passportLastPage");
    }
}
