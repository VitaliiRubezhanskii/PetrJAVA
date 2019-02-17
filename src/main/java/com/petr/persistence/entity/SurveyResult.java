package com.petr.persistence.entity;

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

public class SurveyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_survey")
    private Survey survey;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "surveyResult")
    private List<QuestionResult> questionResults;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "bonus")
    private int bonus;

    @Override
    public String toString(){
        return "";
    }


}
