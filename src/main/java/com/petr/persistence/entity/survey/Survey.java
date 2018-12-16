package com.petr.persistence.entity.survey;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petr.persistence.entity.Question;
import com.petr.persistence.entity.Status;
import com.petr.persistence.entity.SurveyResult;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.UNPUBLISH;

    private String date = new Date().toString();

    private Integer count;

    private Integer passed = 0;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Question> questions;

    @OneToMany(mappedBy = "survey")
    @JsonIgnore
    private List<SurveyResult> surveyResults;

    @OneToMany(mappedBy = "survey")
    @JsonIgnore
    private List<SurveyLimit> surveyLimits;


}
