package com.petr.transport.dto.survetLimit;

import com.petr.persistence.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyLimitAggregateDto {

    private Long id;
    private String region;
    private String surveyName;
    private List<Gender> gender;
    private List<String> ageRange;
    private int count;
    private long matchedUsersCount;

}
