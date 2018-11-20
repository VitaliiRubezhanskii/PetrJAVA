package com.petr.exception.user;

public class PhotoException extends RuntimeException {

    public PhotoException() {
        super("User has photo");
    }
}
