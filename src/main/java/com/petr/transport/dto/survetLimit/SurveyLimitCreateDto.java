package com.petr.transport.dto.survetLimit;

import com.petr.persistence.entity.Gender;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class SurveyLimitCreateDto {

//    @NotNull
    private Integer location;

//    @NotNull
//    @Min(1)
    private Integer minAge;

//    @NotNull
//    @Min(1)
    private Integer maxAge;

//    @NotNull
    private Gender gender;


//    @NotNull
//    @Min(1)
    private Integer count;
}
