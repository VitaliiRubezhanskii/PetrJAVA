package com.petr.exception.user;

public class UserPhoneExistsException extends RuntimeException {

    public UserPhoneExistsException() {
        super("Phone exists");
    }
}
