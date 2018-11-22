package com.petr.exception.user;

public class UserDeletedException extends RuntimeException{

    public UserDeletedException() {
        super("User deleted");
    }
}
