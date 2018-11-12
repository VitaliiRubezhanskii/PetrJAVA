package com.petr.exception;

public class UserPassportExistsException extends RuntimeException {

    public UserPassportExistsException() {
        super("Passport exists");
    }
}
