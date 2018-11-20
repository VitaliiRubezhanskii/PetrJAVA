package com.petr.exception.bank;

public class BankNotVisibleException extends RuntimeException {

    public BankNotVisibleException() {
        super("Bank not visible");
    }
}
