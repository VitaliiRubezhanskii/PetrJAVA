package com.petr.service.survey;

import com.petr.exception.survey.SurveyExistsException;
import com.petr.exception.survey.SurveyNotFoundException;
import com.petr.exception.user.UserDeletedException;
import com.petr.exception.user.UserNotFoundException;
import com.petr.exception.user.UserNotVerifyException;
import com.petr.persistence.entity.Gender;
import com.petr.persistence.entity.Status;
import com.petr.persistence.entity.User;
import com.petr.persistence.entity.survey.Survey;
import com.petr.persistence.entity.survey.SurveyLimit;
import com.petr.persistence.repository.SurveyLimitRepository;
import com.petr.persistence.repository.SurveyRepository;
import com.petr.service.user.UserService;
import com.petr.transport.dto.survetLimit.SurveyLimitAggregateDto;
import com.petr.transport.dto.survey.SurveyCreateDto;
import com.petr.transport.dto.survey.SurveyFindDto;
import com.petr.transport.dto.survey.SurveyOutcomeDto;
import com.petr.transport.mapper.SurveyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import static java.util.stream.Collectors.*;
import java.time.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SurveyServiceImpl extends SurveySearchSpecification implements SurveyService {

    private Long oneYearInSeconds = 31536000000L;

    @Autowired
    private UserService userService;

    private SurveyMapper surveyMapper;
    @Autowired
    private SurveyLimitRepository surveyLimitRepository;

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
                surveyFilter(dto),
                pageable
        );
        return result.map(surveyMapper::toDto);
    }

    @Override
    public Long create(SurveyCreateDto dto) {
        validateSurvey(dto);
        return surveyRepository.save(surveyMapper.toEntity(dto)).getId();
    }

    @Override
    public void setStatus(Long id, Status status) {
        getById(id).setStatus(status);
    }


    @Override
    public Map<Long, Long> getSurveyByUser(Long userId, long location) {
        if (userId == null) {
            throw new UserNotFoundException();
        }
        User user = userService.getById(userId);
        if (user.isDeleted()) {
            throw new UserDeletedException();
        }
        if (!user.isVerify()) {
            throw new UserNotVerifyException();
        }
        Map<Long, Long> surveyIds = new HashMap<>();
//        List<Long> surveyIds = new ArrayList<>();
        List<Survey> surveys = surveyRepository.findAllByStatus(Status.ACTIVE);
        for (Survey survey : surveys) {
            if (survey.getCount() > survey.getPassed()) {
                if (!survey.getSurveyLimits().isEmpty()) {
                    for (SurveyLimit surveyLimit : survey.getSurveyLimits()) {
                        if (surveyLimit.getStatus().equals(Status.ACTIVE)) {
                            if (surveyLimit.getCount() > surveyLimit.getPassed()) {
                                if (surveyLimit.getLocation() == "" || surveyLimit.getLocation().equals(location)) {
//                                    Long userYear = ((new Date().getTime() - user.getBirthDate()) / oneYearInSeconds);
//                                    if (userYear >= surveyLimit.getMinAge() && userYear <= surveyLimit.getMaxAge()) {
//                                        if (surveyLimit.getGender().equals(user.getGender())) {
//                                            surveyIds.put(survey.getId(), surveyLimit.getId());
//                                        }
//                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return surveyIds;

    }

    private void validateSurvey(SurveyCreateDto dto) {
        if (surveyRepository.existsByName(dto.getName())) {
            throw new SurveyExistsException();
        }
    }

    @Override
    public List<Survey> findSurveysByUserLimit(Long userId) {
       User user = userService.getById(userId);
        String location = user.getAddress().getOblast();
        String userBirthDate = user.getBirthDate();
        LocalDate date = LocalDate.parse(userBirthDate.substring(6) + "-" + userBirthDate.substring(3,5) + "-"+userBirthDate.substring(0,2));
        int age = Period.between(date, LocalDate.now()).getYears();
        return surveyLimitRepository
                .findLimitByLocationAnd(location,Gender.MALE)
                .stream()
                .filter(limmit -> limmit.getMinAge()<=age && limmit.getMaxAge()>=age)
                .map(limit-> limit.getSurvey())
                .distinct()
                .collect(toList());

    }

    @Override
    public List<Survey> findAll() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey save(Survey survey) {
        return surveyRepository.save(survey);
    }


}
