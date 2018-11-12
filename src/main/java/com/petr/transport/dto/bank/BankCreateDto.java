package com.petr.transport.dto.bank;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class BankCreateDto {

    @NotBlank
    private String name;
}
