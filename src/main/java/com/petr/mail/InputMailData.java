package com.petr.mail;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class InputMailData {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String message;
}
