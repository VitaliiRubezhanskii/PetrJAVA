package com.petr.controller;

import com.petr.persistence.entity.Answer;
import com.petr.persistence.entity.Question;
import com.petr.persistence.entity.SurveyResult;
import com.petr.persistence.entity.User;
import com.petr.persistence.entity.survey.Survey;
import com.petr.persistence.repository.QuestionRepository;
import com.petr.persistence.repository.SurveyRepository;
import com.petr.security.model.Role;
import com.petr.service.answer.AnswerService;
import com.petr.service.surveyResult.SurveyResultService;
import com.petr.service.user.UserService;
import com.petr.transport.dto.surveyResult.SurveyResultDto;
import com.petr.transport.dto.user.UserOutcomeDto;
import com.petr.transport.mapper.SurveyMapper;
import com.petr.transport.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/scoring")
@RequiredArgsConstructor
public class SurveyResultController {

    private final SurveyResultService surveyResultService;
    private final UserService userService;
    private final AnswerService answerService;
    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;
    private final UserMapper userMapper;
    private final SurveyMapper surveyMapper;

    @GetMapping(value = "/user/{userId}")
    public List<SurveyResult> getSurveyResultOfUser(@PathVariable("userId") Long userId){
        User user = userService.getById(userId);
        return surveyResultService.getSurveyResultByUser(user);
    }


    @GetMapping(value = "/users/{userId}/children")
    public List<SurveyResult> getSurveyResultOfChildren(@PathVariable("userId") Long userId) {
      return userService.findUsersByParentId(userId)
                .stream()
                .filter(user -> user.getRoles().stream().map(Role::getName).collect(Collectors.toList()).contains("USER"))
                .map(surveyResultService::getSurveyResultByUser)
                .flatMap(List<SurveyResult>::stream)
                .collect(Collectors.toList());
    }


    @GetMapping(value = "/users")
    public List<UserOutcomeDto> getSurveyResults(){
       Map<User, Integer> bonus = surveyResultService.getSurveyResultOfAll()
               .stream()
               .collect(Collectors.groupingBy(SurveyResult::getUser, Collectors.summingInt(SurveyResult::getBonus)));
       Map<User, Long> countOfSurveys = surveyResultService.getSurveyResultOfAll()
               .stream()
               .collect(Collectors.groupingBy(SurveyResult::getUser, Collectors.counting()));
       return bonus.keySet()
                .stream()
                .map(k-> {
                    long countOfChildrensSurveys = 0L;
                    int totalScoreOfChildren = 0;
                    UserOutcomeDto userOutcomeDto = userMapper.toDto(k);
                    userOutcomeDto.setOwnSurveysCount(countOfSurveys.get(k));
                    userOutcomeDto.setTotalAccountedScoring(bonus.get(k));
                    List<User> childrenUsers = userService.findUsersByParentId(k.getId());
                       long countOfGrandChildrensSurveys =  childrenUsers
                                        .stream()
                                        .map(childrens ->userService.findUsersByParentId(childrens.getId()))
                                        .flatMap(List::stream)
                                        .mapToLong(grands->surveyResultService.getSurveyResultByUser(grands).size())
                                        .sum();
                       int totalScoreOfGrandChildren =childrenUsers
                               .stream()
                               .map(childrens ->userService.findUsersByParentId(childrens.getId()))
                               .flatMap(List::stream)
                               .map(surveyResultService::getSurveyResultByUser)
                               .flatMap(List::stream)
                               .mapToInt(SurveyResult::getBonus).sum();

                             countOfChildrensSurveys = childrenUsers
                                    .stream()
                                    .mapToLong(r -> surveyResultService.getSurveyResultByUser(r).size())
                                    .sum();
                             totalScoreOfChildren = childrenUsers
                                    .stream()
                                    .flatMap(r -> surveyResultService.getSurveyResultByUser(r).stream())
                                    .mapToInt(SurveyResult::getBonus).sum();
                        userOutcomeDto.setCountOfChildrensSurveys(countOfChildrensSurveys);
                        userOutcomeDto.setTotalScoreOfChildren(totalScoreOfChildren);
                        userOutcomeDto.setCountOfGrandChildrensSurveys(countOfGrandChildrensSurveys);
                        userOutcomeDto.setTotalScoreOfGrandChildren(totalScoreOfGrandChildren);
                        return userOutcomeDto;
                })
                .collect(Collectors.toList());
    }
}
