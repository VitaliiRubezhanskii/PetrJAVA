package com.petr.petr.service.survey;

import com.petr.petr.exception.SurveyExistsException;
import com.petr.petr.exception.SurveyNotFoundException;
import com.petr.petr.persistence.entity.Survey;
import com.petr.petr.persistence.repository.SurveyRepository;
import com.petr.petr.transport.dto.survey.SurveyCreateDto;
import com.petr.petr.transport.dto.survey.SurveyFindDto;
import com.petr.petr.transport.dto.survey.SurveyOutcomeDto;
import com.petr.petr.transport.mapper.SurveyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SurveyServiceImpl implements SurveyService {


    private SurveyMapper surveyMapper;

    @Autowired
    public void setSurveyMapper(SurveyMapper surveyMapper) {
        this.surveyMapper = surveyMapper;
    }

    @Autowired
    private SurveyRepository surveyRepository;

    @Override
    public Survey getById(Long id) {
        return surveyRepository.findById(id).orElseThrow(SurveyNotFoundException::new);
    }

    @Override
    public Page<SurveyOutcomeDto> getAll(SurveyFindDto dto, Pageable pageable) {
        Page<Survey> result = surveyRepository.findAll(
                SurveySearchSpecification.surveyFilter(dto),
                pageable
        );
        return result.map(surveyMapper::toDto);
    }

    @Override
    public Long create(SurveyCreateDto dto) {
        validateSurvey(dto);
        return surveyRepository.save(surveyMapper.toEntity(dto)).getId();
    }

    private void validateSurvey(SurveyCreateDto dto) {
        if (surveyRepository.existsByName(dto.getName())) {
            throw new SurveyExistsException();
        }
    }
}
