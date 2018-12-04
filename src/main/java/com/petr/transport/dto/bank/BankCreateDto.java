package com.petr.transport.dto.bank;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Data
public class BankCreateDto {

    @NotBlank
    private String name;


}
