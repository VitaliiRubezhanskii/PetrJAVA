package com.petr.transport.dto.survey;

import lombok.Data;

import java.util.List;

@Data
public class SurveyOutcomeDto {

    private Long id;

    private String name;

    private Boolean deleted;

    private List<Long> questions;
}
