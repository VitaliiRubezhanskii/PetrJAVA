package com.petr.transport.dto.user;

import com.petr.persistence.entity.Gender;
import com.petr.persistence.entity.RoleType;
import lombok.Data;

@Data
public class UserFindDto {

    private Long id;

    private RoleType roleType;

    private Gender gender;

    private boolean verify ;

    private boolean deleted ;

    private String name;

    private String surname;

    private String middleName;

    private Long startBirthDate;

    private Long finishBirthDate;

    private Long startDate;

    private Long finishDate;

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

    private String photoInn;

    private Long bank;

    private String card;

    private Long parentId;
}
