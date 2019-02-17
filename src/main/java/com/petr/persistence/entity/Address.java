package com.petr.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "oblast")
    private String oblast;

    @Column(name = "city")
    private String city;

    @Column(name="street")
    private String street;

    @Column(name = "buildingNum")
    private String buildingNum;

    @Column(name = "apartmentNum")
    private String apartmentNum;

//    @OneToMany(mappedBy = "address")
//    @JsonIgnore
//    private Set<User> users;


}
