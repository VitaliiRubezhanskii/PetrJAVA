package com.petr.petr.exception;

public class UserPhoneExistsException extends RuntimeException {

    public UserPhoneExistsException() {
        super("Phone exists");
    }
}
