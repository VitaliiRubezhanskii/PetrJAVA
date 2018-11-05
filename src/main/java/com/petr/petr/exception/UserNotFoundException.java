package com.petr.petr.exception;

public class UserNotFoundException  extends RuntimeException{

    public UserNotFoundException() {
        super("User not found");
    }
}
