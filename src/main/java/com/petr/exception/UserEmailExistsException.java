package com.petr.exception;

public class UserEmailExistsException extends RuntimeException {

    public UserEmailExistsException() {
        super("Email exists");
    }
}
