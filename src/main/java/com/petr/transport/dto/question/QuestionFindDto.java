package com.petr.transport.dto.question;

import com.petr.persistence.entity.QuestionType;
import lombok.Data;

import java.util.List;

@Data
public class QuestionFindDto {

    private Long id;

    private String text;

    private QuestionType type;

    private Long survey;

    private Boolean deleted;

    private List<Long> answers;
}
