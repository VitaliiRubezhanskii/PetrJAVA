package com.petr.exception.user;

public class UserNotVerifyException extends RuntimeException{

    public UserNotVerifyException() {
        super("User not verify");
    }
}
