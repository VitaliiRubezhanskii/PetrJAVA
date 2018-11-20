package com.petr.transport.dto.survey;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SurveyCreateDto {

    @NotBlank
    @NotNull
    private String name;

    @NotNull
    @Min(1)
    private Integer count;


    public String getName() {
        return name.toLowerCase();
    }
}
