package com.petr.transport.dto.answer;

import com.petr.persistence.entity.Status;
import lombok.Data;

@Data
public class AnswerFindDto {

    private Long id;

    private String text;

    private Status status;

    private Long question;

    private Long finishDate;

    private Long startDate;
}
