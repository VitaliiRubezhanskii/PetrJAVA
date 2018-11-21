package com.petr.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.UNPUBLISH;

    private Long date = new Date().getTime();

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
