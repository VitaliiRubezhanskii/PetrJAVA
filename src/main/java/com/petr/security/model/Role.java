package com.petr.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petr.persistence.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

//    @ManyToMany(mappedBy = "roles")
//    @JsonIgnore
//    private Set<User> users;

}
