package com.petr.petr.transport.dto;

import com.petr.petr.persistence.entity.QuestionType;
import lombok.Data;

@Data
public class QuestionCreateDto {

    private String text;

    private boolean deleted;

    private QuestionType type;

    private Long surveyId;
}
