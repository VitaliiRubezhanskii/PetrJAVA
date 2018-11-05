package com.petr.petr.transport.dto;

import com.petr.petr.persistence.entity.Role;
import lombok.Data;

@Data
public class UserFindDto {

    private Long id;

    private Role role;

    private boolean verify = false;

    private String name;

    private String surname;

    private String patronymic;

    private Long StartBirthDate;

    private Long FinishBirthDate;

    private String phone;

    private String email;

    private String inn;

    private String passport;

    private String issuedBy;

    private String issuedWhen;

    private String passwordFirstPage;

    private String passwordSecondPage;

    private String passwordLastPage;

    private String photo;

    private Long bank;

    private String card;

    private Long parentId;
}
