package com.petr.petr.transport.dto;

import com.petr.petr.persistence.entity.Role;
import lombok.Data;

@Data
public class UserOutcomeDto {

    private Long id;

    private Role role;

    private boolean verify = false;

    private String name;

    private String surname;

    private String patronymic;

    private Long birthDate;

    private String phone;

    private String email;

    private Integer inn;

    private String passport;

    private String issuedBy;

    private String issuedWhen;

    private String passwordFirstPage;

    private String passwordSecondPage;

    private String passwordLastPage;

    private String photoInn;

    private String photo;

    private Long bank;

    private String card;

    private Long parentId;
}
