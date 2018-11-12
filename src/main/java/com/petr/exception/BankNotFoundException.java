package com.petr.exception;

public class BankNotFoundException extends RuntimeException{

    public BankNotFoundException() {
        super("Bank not found");
    }
}
