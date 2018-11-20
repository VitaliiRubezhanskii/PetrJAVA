package com.petr.exception.user;

public class UserNotFoundException  extends RuntimeException{

    public UserNotFoundException() {
        super("User not found");
    }
}
