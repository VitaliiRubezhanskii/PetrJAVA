package com.petr.transport.dto.survetLimit;

import com.petr.persistence.entity.Gender;
import lombok.Data;

import java.util.List;

@Data
public class SurveyLimitDto {

    private List<String> ageGroup;
    private  List<Gender> gender;
    private Long survey;
    private int countOfSurveys;
    private String region;


}
