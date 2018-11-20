package com.petr.transport.dto.survetLimit;

import com.petr.persistence.entity.Gender;
import com.petr.persistence.entity.Status;
import lombok.Data;

@Data
public class SurveyLimitOutcomeDto {

    private Long id;

    private Integer location;

    private Integer minAge;

    private Integer maxAge;

    private Gender gender;

    private Integer count;

    private Integer passed;

    private Status status;

    private Long survey;
}
