package com.petr.petr.transport.dto.bank;

import lombok.Data;

import java.util.List;

@Data
public class BankFindDto {


    private Long id;

    private Boolean visible;

    private String name;

    private List<Long> users;
}
