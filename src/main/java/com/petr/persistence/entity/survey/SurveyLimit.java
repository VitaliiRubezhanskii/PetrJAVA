package com.petr.persistence.entity.survey;

import com.petr.persistence.entity.Gender;
import com.petr.persistence.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer location;

    private Integer minAge;

    private Integer maxAge;

    private Gender gender;

    private Integer count;

    private Integer passed = 0;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.UNPUBLISH;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

}
