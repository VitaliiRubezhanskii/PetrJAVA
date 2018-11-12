package com.petr.transport.dto.user;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserCreateDto {

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String surname;

    @NotBlank
    @NotNull
    private String patronymic;

    @NotNull
    private Long birthDate;

    @NotBlank
    @NotNull
    @Size(min = 13, max = 13)
    private String phone;

    @Email
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @Size(min = 10, max = 10)
    private String inn;

    @NotNull
    @Size(min = 8, max = 8)
    private String passport;

    @NotBlank
    private String issuedBy;

    @NotBlank
    private String issuedWhen;

    private Long parentId;

    @NotNull
    private Long bank;

    @NotNull
    @NotBlank
    @Size(min = 16, max = 16)
    private String card;


}
