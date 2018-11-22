package com.petr.transport.dto.bank;

import lombok.Data;

import java.util.List;

@Data
public class BankFindDto {


    private Long id;

    private Boolean deleted;

    private String name;

    private List<Long> users;

    private Long finishDate;

    private Long startDate;
}
