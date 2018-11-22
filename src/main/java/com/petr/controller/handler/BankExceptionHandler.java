package com.petr.controller.handler;

import com.petr.exception.bank.BankExistsException;
import com.petr.exception.bank.BankNotFoundException;
import com.petr.exception.bank.BankDeletedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BankExceptionHandler {

    @ExceptionHandler(value = BankExistsException.class)
    private String bankExistsException(BankExistsException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = BankNotFoundException.class)
    private String bankNotFoundException(BankNotFoundException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = BankDeletedException.class)
    private String bankNotVisibleException(BankDeletedException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }
}
