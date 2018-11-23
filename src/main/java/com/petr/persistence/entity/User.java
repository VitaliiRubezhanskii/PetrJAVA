package com.petr.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import com.petr.security.model.Role;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "roleType", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role = RoleType.USER;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private boolean deleted = false;

    @Column(nullable = false)
    private boolean verify = false;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String patronymic;

    @Column(nullable = false)
    private Long birthDate;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String inn;

    @Column(nullable = false, unique = true)
    private String passport;

    @Column(nullable = false)
    private String issuedBy;

    @Column(nullable = false)
    private String issuedWhen;

    private String passwordFirstPage;

    private String passwordSecondPage;

    private String passwordLastPage;

    private String photoInn;

    private String photo;

    private Long date = new Date().getTime();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Bank bank;

    @Column(nullable = false)
    private String card;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId_id")
    private User parentId;

    @OneToMany(mappedBy = "user")
    private List<SurveyResult> surveyResults;

    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID") })
    private Set<Role> roles;

}
