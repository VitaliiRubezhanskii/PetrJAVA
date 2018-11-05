package com.petr.petr.exception;

public class UserINNExistsException extends RuntimeException {

    public UserINNExistsException() {
        super("INN exists");
    }
}
