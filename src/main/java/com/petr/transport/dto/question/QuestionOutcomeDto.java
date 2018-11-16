package com.petr.transport.dto.question;

import com.petr.persistence.entity.QuestionType;
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

    private boolean deleted ;

    private List<Long> answers;

    private Long date;

    private Boolean required;
}
