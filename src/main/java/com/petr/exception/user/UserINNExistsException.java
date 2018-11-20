package com.petr.exception.user;

public class UserINNExistsException extends RuntimeException {

    public UserINNExistsException() {
        super("INN exists");
    }
}
