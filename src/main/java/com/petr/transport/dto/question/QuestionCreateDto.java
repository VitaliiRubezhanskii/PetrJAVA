package com.petr.transport.dto.question;

import com.petr.persistence.entity.QuestionType;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class QuestionCreateDto {

    @NotBlank
    @NotNull
    private String text;

    @NotNull
    @Min(1)
    private Integer min;

    @NotNull
    @Min(1)
    private Integer max ;

    @NotNull
    private Boolean required;

    @NotNull
    private QuestionType type;
}
