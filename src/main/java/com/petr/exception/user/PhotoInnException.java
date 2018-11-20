package com.petr.exception.user;

public class PhotoInnException extends RuntimeException {

    public PhotoInnException() {
        super("User has photoInn");
    }
}
