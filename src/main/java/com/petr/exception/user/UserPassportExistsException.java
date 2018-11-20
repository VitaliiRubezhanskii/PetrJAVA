package com.petr.exception.user;

public class UserPassportExistsException extends RuntimeException {

    public UserPassportExistsException() {
        super("Passport exists");
    }
}
