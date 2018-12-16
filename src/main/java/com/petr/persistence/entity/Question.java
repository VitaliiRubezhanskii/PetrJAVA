package com.petr.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petr.persistence.entity.survey.Survey;
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
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.UNPUBLISH;

//    @Enumerated(EnumType.STRING)
    private QuestionType type;

    private Integer min;

    private Integer max;

    private Boolean required;

    private String date = new Date().toString();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "survey_id")
    @JsonIgnore
    private Survey survey;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;
}
