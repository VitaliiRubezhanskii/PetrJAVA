package com.petr.transport.dto.question;

import com.petr.persistence.entity.QuestionType;
import com.petr.persistence.entity.Status;
import lombok.Data;

import java.util.List;

@Data
public class QuestionOutcomeDto {

    private Long id;

    private String text;

    private QuestionType type;

    private Long survey;

    private Integer min;

    private Integer max;

    private Status status;

    private List<Long> answers;

    private String date;

    private Boolean required;
}
