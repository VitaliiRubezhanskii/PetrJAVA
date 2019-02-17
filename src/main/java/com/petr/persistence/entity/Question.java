package com.petr.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petr.persistence.entity.survey.Survey;
import lombok.*;
import org.apache.commons.lang.builder.ToStringBuilder;

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

    @ManyToOne
    @JoinColumn(name = "survey_id")
    @JsonIgnore
    private Survey survey;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;

    @Override
    public String toString(){
        return  new ToStringBuilder(this)
                .append("id: ",id)
                .append("text: ", text)
                .append("status: ", status)
                .append("type: ", type).toString();
    }

}
