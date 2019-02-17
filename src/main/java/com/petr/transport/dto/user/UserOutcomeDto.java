package com.petr.transport.dto.user;

import com.petr.persistence.entity.Address;
import com.petr.persistence.entity.Gender;
import com.petr.persistence.entity.RoleType;
import lombok.Data;

@Data
public class UserOutcomeDto {

    private Long id;

    private RoleType roleType;

    private Gender gender;

    private boolean verify;

    private boolean deleted;

    private String name;

    private String surname;

    private String middleName;

    private String birthDate;

    private Long date;

    private String phone;

    private String email;

    private String inn;

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

    private String oblast;
    private String city;
    private String street;
    private String building;
    private String apartment;
}
