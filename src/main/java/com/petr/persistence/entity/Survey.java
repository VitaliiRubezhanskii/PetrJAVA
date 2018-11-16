package com.petr.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private boolean deleted = true;

    private Long date = new Date().getTime();

    @OneToMany(mappedBy = "survey")
    private List<Question> questions;

    @OneToMany(mappedBy = "survey")
    private List<SurveyResult> surveyResults;
}