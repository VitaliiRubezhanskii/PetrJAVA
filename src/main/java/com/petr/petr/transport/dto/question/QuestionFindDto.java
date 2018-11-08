package com.petr.petr.transport.dto.question;

import com.petr.petr.persistence.entity.QuestionType;
import lombok.Data;

@Data
public class QuestionFindDto {

    private Long id;

    private String text;

    private QuestionType type;

    private Long survey;

    private Boolean deleted;
}
