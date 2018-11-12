package com.petr.transport.dto.question;

import com.petr.persistence.entity.QuestionType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class QuestionCreateDto {

    @NotBlank
    @NotNull
    private String text;

    @NotNull
    private QuestionType type;
}
