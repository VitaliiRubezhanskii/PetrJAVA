package com.petr.exception;

public class UserPhoneExistsException extends RuntimeException {

    public UserPhoneExistsException() {
        super("Phone exists");
    }
}
