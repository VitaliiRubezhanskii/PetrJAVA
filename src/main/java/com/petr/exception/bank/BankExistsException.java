package com.petr.exception.bank;

public class BankExistsException extends RuntimeException {

    public BankExistsException() {
        super("Bank name exists");
    }
}
