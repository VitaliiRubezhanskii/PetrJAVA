package com.petr.transport.dto.survey;

import com.petr.persistence.entity.Status;
import lombok.Data;

import java.util.List;

@Data
public class SurveyOutcomeDto {

    private Long id;

    private String name;

    private Status status;

    private List<Long> questions;

    private String date;

    private List<Long> surveyLimits;

    private Integer count;

    private Integer passed;
}
