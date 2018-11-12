package com.petr.exception;

public class BankExistsException extends RuntimeException {

    public BankExistsException() {
        super("Bank name exists");
    }
}
