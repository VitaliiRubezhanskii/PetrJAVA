package com.petr.service.surveyLimit;

import com.petr.exception.survey.*;
import com.petr.persistence.entity.Status;
import com.petr.persistence.entity.survey.Survey;
import com.petr.persistence.entity.survey.SurveyLimit;
import com.petr.persistence.repository.SurveyLimitRepository;
import com.petr.service.survey.SurveyService;
import com.petr.transport.dto.survetLimit.*;
import com.petr.transport.mapper.SurveyLimitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class SurveyLimitServiceImpl extends SurveyLimitSearchSpecification implements SurveyLimitService {

    private SurveyLimitMapper surveyLimitMapper;
    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyLimitRepository surveyLimitRepository;

    @Autowired
    public void setSurveyLimitMapper(SurveyLimitMapper surveyLimitMapper) {
        this.surveyLimitMapper = surveyLimitMapper;
    }


    @Override
    public SurveyLimit getById(Long id) {
        return surveyLimitRepository.findById(id).orElseThrow(SurveyLimitNotFoundException::new);
    }

    @Override
    public Page<SurveyLimitOutcomeDto> getAll(SurveyLimitFindDto dto, Pageable pageable) {
        Page<SurveyLimit> result = surveyLimitRepository.findAll(
                surveyLimitFilter(dto),
                pageable
        );
        return result.map(surveyLimitMapper::toDto);
    }

    @Override
    public Long create(SurveyLimitCreateDto dto, Long surveyId) {
        validateSurveyLimit(dto, surveyId);
        SurveyLimit surveyLimit = surveyLimitMapper.toEntity(dto);
        Survey survey = surveyService.getById(surveyId);
        surveyLimit.setSurvey(survey);
        return surveyLimitRepository.save(surveyLimit).getId();
    }

    @Override
    public List<SurveyLimit> save(SurveyLimitDto dto, Long surveyId) {
        List<SurveyLimit> limitsForFirstGender = new ArrayList<>();
        List<SurveyLimit> limitsForSecondGender = new ArrayList<>();
        List<SurveyLimit> resultingLimits = new ArrayList<>();
        for (String group : dto.getAgeGroup()){
            SurveyLimit limit = new SurveyLimit();
            limit.setLocation(dto.getRegion());
            limit.setSurvey(surveyService.getById(dto.getSurvey()));
            limit.setGender(dto.getGender().get(0));
            limit.setMinAge(Integer.parseInt(group.substring(0,2)));
            limit.setMaxAge(Integer.parseInt(group.substring(5,7)));
            limit.setCount(dto.getCountOfSurveys());
            limitsForFirstGender.add(limit);
        }
        if (dto.getGender().size()>1) {
            for (String group : dto.getAgeGroup()) {
                SurveyLimit limit = new SurveyLimit();
                limit.setLocation(dto.getRegion());
                limit.setSurvey(surveyService.getById(dto.getSurvey()));
                limit.setGender(dto.getGender().get(1));
                limit.setMinAge(Integer.parseInt(group.substring(0,2)));
                limit.setMaxAge(Integer.parseInt(group.substring(5,7)));
                limit.setCount(dto.getCountOfSurveys());
                limitsForFirstGender.add(limit);
            }
        }
        resultingLimits.addAll(limitsForFirstGender);
        resultingLimits.addAll(limitsForSecondGender);
        resultingLimits.forEach(limit-> surveyLimitRepository.save(limit));
        return resultingLimits;

    }

    @Override
    public void setStatus(Long id, Status status){
        getById(id).setStatus(status);
    }



    @Override
    public List<Long> getIdFromEntity(List<SurveyLimit> surveyLimits) {
        if (surveyLimits == null) {
            return null;
        }
        List<Long> surveyLimitIds = new ArrayList<>();
        for (SurveyLimit surveyLimit : surveyLimits) {
            surveyLimitIds.add(surveyLimit.getId());
        }
        return surveyLimitIds;
    }

    private void validateSurveyLimit(SurveyLimitCreateDto dto, Long surveyId) {
        if (dto.getMaxAge() < dto.getMinAge()) {
            throw new SurveyLimitMinMaxException();
        }
        Survey survey = surveyService.getById(surveyId);
        if (survey.getStatus().equals(Status.DELETED)) {
            throw new SurveyDeletedException();
        }
        int count = 0;
        for (SurveyLimit surveyLimit : survey.getSurveyLimits()) {
            count += surveyLimit.getCount();
        }
        if (survey.getCount() < dto.getCount() || count >= survey.getCount()) {
            throw new SurveyLimitCountException();
        }
    }

    @Override
    public List<SurveyLimitAggregateDto> getSurveyLimits() {
        Map<Survey, List<SurveyLimit>> limitsGroupped = surveyLimitRepository.findAll()
                .stream()
                .collect(groupingBy(SurveyLimit::getSurvey, toList()));
        return limitsGroupped.keySet().stream()
                .map(survey -> {
                    SurveyLimitAggregateDto surveyLimitAggregateDto = new SurveyLimitAggregateDto();
                    surveyLimitAggregateDto.setId(survey.getId());
                    surveyLimitAggregateDto.setSurveyName(survey.getName());
                    surveyLimitAggregateDto.setRegion(limitsGroupped.get(survey).get(0).getLocation());
                    surveyLimitAggregateDto.setCount(limitsGroupped.get(survey).get(0).getCount());
                    surveyLimitAggregateDto.setGender(limitsGroupped.get(survey).stream().map(SurveyLimit::getGender).distinct().collect(toList()));
                    List<String> ageRange =  limitsGroupped.get(survey).stream()
                            .map(limit -> {
                                StringBuilder ageGroup = new StringBuilder();
                                return ageGroup.append(limit.getMinAge()).append(" - ").append(limit.getMaxAge()).toString();
                            }).distinct().collect(toList());
                    surveyLimitAggregateDto.setAgeRange(ageRange);
                    return surveyLimitAggregateDto;
                }).collect(toList());
    }
}
