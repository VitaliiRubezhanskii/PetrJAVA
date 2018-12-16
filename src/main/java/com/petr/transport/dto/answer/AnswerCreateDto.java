package com.petr.transport.dto.answer;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data

public class AnswerCreateDto {

//    @NotBlank
//    @NotNull
    private String value;
}
