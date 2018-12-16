package com.petr.transport.dto.answer;

import com.petr.persistence.entity.Status;
import lombok.Data;

@Data
public class AnswerOutcomeDto {

    private Long id;

    private String text;

    private Status status;

    private Long question;

    private String date;
}
