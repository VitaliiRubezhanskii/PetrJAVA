package com.petr.transport.dto.question;

import com.petr.persistence.entity.QuestionType;
import com.petr.persistence.entity.Status;
import lombok.Data;

import java.util.List;

@Data
public class QuestionFindDto {

    private Long id;

    private String text;

    private QuestionType type;

    private Long survey;

    private Integer min;

    private Integer max;

    private Status status;

    private List<Long> answers;

    private Long finishDate;

    private Long startDate;

    private Boolean required;
}
