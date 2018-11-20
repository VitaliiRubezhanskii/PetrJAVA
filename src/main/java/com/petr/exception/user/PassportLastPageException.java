package com.petr.exception.user;

public class PassportLastPageException extends RuntimeException {

    public PassportLastPageException() {
        super("User has passportLastPage");
    }
}
