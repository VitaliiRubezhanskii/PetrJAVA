package com.petr.exception;

public class PhotoException extends RuntimeException {

    public PhotoException() {
        super("User has photo");
    }
}
