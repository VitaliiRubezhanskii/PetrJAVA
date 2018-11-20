package com.petr.exception.bank;

public class BankNotFoundException extends RuntimeException{

    public BankNotFoundException() {
        super("Bank not found");
    }
}
