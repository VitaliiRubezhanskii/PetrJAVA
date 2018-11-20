package com.petr.service.surveyLimit;

import com.petr.exception.survey.SurveyDeletedException;
import com.petr.exception.survey.SurveyLimitCountException;
import com.petr.exception.survey.SurveyLimitMinMaxException;
import com.petr.exception.survey.SurveyNotFoundException;
import com.petr.persistence.entity.Status;
import com.petr.persistence.entity.survey.Survey;
import com.petr.persistence.entity.survey.SurveyLimit;
import com.petr.persistence.repository.SurveyLimitRepository;
import com.petr.service.survey.SurveyService;
import com.petr.transport.dto.survetLimit.SurveyLimitCreateDto;
import com.petr.transport.dto.survetLimit.SurveyLimitFindDto;
import com.petr.transport.dto.survetLimit.SurveyLimitOutcomeDto;
import com.petr.transport.mapper.SurveyLimitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyLimitServiceImpl extends SurveyLimitSearchSpecification implements SurveyLimitService {

    private SurveyLimitMapper surveyLimitMapper;

    @Autowired
    public void setSurveyLimitMapper(SurveyLimitMapper surveyLimitMapper) {
        this.surveyLimitMapper = surveyLimitMapper;
    }

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyLimitRepository surveyLimitRepository;

    @Override
    public SurveyLimit getById(Long id) {
        return surveyLimitRepository.findById(id).orElseThrow(SurveyNotFoundException::new);
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
        return surveyLimitRepository.save(surveyLimitMapper.toEntity(dto)).getId();
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
        if (survey.getStatus().equals(Status.DELETED)){
            throw new SurveyDeletedException();
        }
        if (survey.getCount()<dto.getCount()){
            throw new SurveyLimitCountException();
        }

    }
}
