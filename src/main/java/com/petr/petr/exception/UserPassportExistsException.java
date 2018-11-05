package com.petr.petr.exception;

public class UserPassportExistsException extends RuntimeException {

    public UserPassportExistsException() {
        super("Passport exists");
    }
}
