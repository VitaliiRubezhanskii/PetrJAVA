package com.petr.transport.dto.survey;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SurveyCreateDto {

    @NotBlank
    @NotNull
    private String name;

//    private List<Long> questionIds;


    public String getName() {
        return name.toLowerCase();
    }
}
