package com.petr.transport.dto.bank;

import lombok.Data;

import java.util.List;

@Data
public class BankOutcomeDto {

    private Long id;

    private Boolean visible;

    private String name;

    private List<Long> users;

    private Long date;
}