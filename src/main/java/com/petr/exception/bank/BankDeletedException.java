package com.petr.exception.bank;

public class BankDeletedException extends RuntimeException {

    public BankDeletedException() {
        super("Bank deleted");
    }
}
