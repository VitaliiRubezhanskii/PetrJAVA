package com.petr.petr.controller.handler;

import com.petr.petr.exception.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = BankExistsException.class)
    private String bankExistsException(BankExistsException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = BankNotFoundException.class)
    private String bankNotFoundException(BankNotFoundException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = PassportFistPageException.class)
    private String passportFistPageException(PassportFistPageException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = PassportSecondPageException.class)
    private String passportSecondPageException(PassportSecondPageException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = UserEmailExistsException.class)
    private String userEmailExistsException(UserEmailExistsException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = UserINNExistsException.class)
    private String userINNExistsException(UserINNExistsException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    private String userNotFoundException(UserNotFoundException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = UserPassportExistsException.class)
    private String userPassportExistsException(UserPassportExistsException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = UserPhoneExistsException.class)
    private String userPhoneExistsException(UserPhoneExistsException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

}
