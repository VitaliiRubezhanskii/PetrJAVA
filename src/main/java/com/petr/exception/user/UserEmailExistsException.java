package com.petr.exception.user;

public class UserEmailExistsException extends RuntimeException {

    public UserEmailExistsException() {
        super("Email exists");
    }
}
