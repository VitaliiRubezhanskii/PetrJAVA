package com.petr.transport.dto.answer;

import lombok.Data;

@Data
public class AnswerOutcomeDto {

    private Long id;

    private String text;

    private Boolean deleted;

    private Long question;
}
