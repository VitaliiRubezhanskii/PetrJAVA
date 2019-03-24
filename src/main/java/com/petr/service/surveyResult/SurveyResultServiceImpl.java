package com.petr.service.surveyResult;

import com.petr.persistence.entity.SurveyResult;
import com.petr.persistence.entity.User;
import com.petr.persistence.repository.SurveyResultRepository;
import com.petr.service.answer.AnswerService;
import com.petr.service.user.UserService;
import com.petr.transport.dto.surveyResult.QuestionResultDto;
import com.petr.transport.dto.surveyResult.SurveyUserResultDto;
import com.petr.transport.dto.user.UserOutcomeDto;
import com.petr.transport.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;


@Service
@RequiredArgsConstructor
public class SurveyResultServiceImpl implements SurveyResultService {

    private final SurveyResultRepository surveyResultRepository;
    private final UserMapper userMapper;
    private final UserService userService;
    private final AnswerService answerService;

    @Override
    public void submit(SurveyResult survey) {
        surveyResultRepository.save(survey);
    }

    @Override
    public List<SurveyResult> getSurveyResultByUser(User user) {
        return surveyResultRepository.getSurveyResultByUser(user);
    }

    @Override
    public List<SurveyUserResultDto> getAllSurveyUserResults() {
        Map<User, List<SurveyResult>> userSurveyResults = surveyResultRepository.findAll()
                .stream()
                .collect(groupingBy(SurveyResult::getUser,toList()));
        return userSurveyResults.keySet()
                .stream()
                .map(user -> {
                    return userSurveyResults.get(user).stream()
                            .map(result -> {
                                SurveyUserResultDto surveyUserResultDto = new SurveyUserResultDto();
                                surveyUserResultDto.setUserFullName(user.getName() + " " + user.getMiddleName() + " " + user.getSurname());
                                surveyUserResultDto.setSurveyName(result.getSurvey().getName());
                                surveyUserResultDto.setSurveySubmissionDate(result.getDate().toString());

                                Map<String, List<QuestionResultDto>> questionaryAnswerMap = answerService.findAnswersByUsers(user).stream()
                                        .map(answer -> {
                                            QuestionResultDto questionResultDto = new QuestionResultDto();
                                            questionResultDto.setUserFullName(answer.getQuestion().getText());
                                            questionResultDto.setSurveyName(answer.getValue());
                                           return questionResultDto;
                                        }).collect(groupingBy(QuestionResultDto::getUserFullName, toList()));

                                List<QuestionResultDto> options = questionaryAnswerMap.keySet().stream()
                                        .map(question -> {
                                            QuestionResultDto questionResultDto = new QuestionResultDto();
                                            questionResultDto.setUserFullName(question);
                                            StringBuilder builder = new StringBuilder();
                                            AtomicInteger counter = new AtomicInteger(0);
                                            questionaryAnswerMap.get(question).stream()
                                                    .map(QuestionResultDto::getSurveyName)
                                                    .forEach(option -> builder.append(" ").append(counter.incrementAndGet())
                                                            .append(".").append(option));
                                            questionResultDto.setSurveyName(builder.toString());
                                            return questionResultDto;
                                        }).collect(toList());

                                surveyUserResultDto.setQuestionResultDtos(options);
                                return surveyUserResultDto;
                            }).collect(toList());
                    }).flatMap(List::stream)
                .sorted(comparing(SurveyUserResultDto::getUserFullName))
                .collect(toList());

    }

    @Override
    public List<UserOutcomeDto> getSurveyResults() {
        List<SurveyResult> surveyResults = surveyResultRepository.findAll();
        return prepareSurveyResults(surveyResults);
    }

    @Override
    public List<UserOutcomeDto> getUserSurveyResults(User user) {
        List<SurveyResult> surveyResults = getSurveyResultByUser(user);
        return prepareSurveyResults(surveyResults);
    }

    private List<UserOutcomeDto> prepareSurveyResults(List<SurveyResult> surveyResults){
        Map<User, Integer> bonus =  surveyResults
                .stream()
                .collect(groupingBy(SurveyResult::getUser, summingInt(SurveyResult::getBonus)));
        Map<User, Long> countOfSurveys = surveyResultRepository.findAll()
                .stream()
                .collect(groupingBy(SurveyResult::getUser, counting()));
        return bonus.keySet()
                .stream()
                .map(user -> {
                    UserOutcomeDto userOutcomeDto = userMapper.toDto(user);
                    userOutcomeDto.setOwnSurveysCount(countOfSurveys.get(user));
                    userOutcomeDto.setTotalAccountedScoring(bonus.get(user));
                    List<User> childrenUsers = userService.findUsersByParentId(user.getId());
                    long countOfGrandChildrensSurveys = childrenUsers.stream()
                            .map(childrens -> userService.findUsersByParentId(childrens.getId()))
                            .flatMap(List::stream)
                            .mapToLong(grands -> getSurveyResultByUser(grands).size())
                            .sum();
                    int totalScoreOfGrandChildren = childrenUsers
                            .stream()
                            .map(childrens -> userService.findUsersByParentId(childrens.getId()))
                            .flatMap(List::stream)
                            .map(this::getSurveyResultByUser)
                            .flatMap(List::stream)
                            .mapToInt(SurveyResult::getBonus).sum();
                    long countOfChildrensSurveys = childrenUsers
                            .stream()
                            .mapToLong(r -> getSurveyResultByUser(r).size())
                            .sum();
                    int totalScoreOfChildren = childrenUsers
                            .stream()
                            .flatMap(r -> getSurveyResultByUser(r).stream())
                            .mapToInt(SurveyResult::getBonus).sum();
                    userOutcomeDto.setCountOfChildrensSurveys(countOfChildrensSurveys);
                    userOutcomeDto.setTotalScoreOfChildren(totalScoreOfChildren);
                    userOutcomeDto.setCountOfGrandChildrensSurveys(countOfGrandChildrensSurveys);
                    userOutcomeDto.setTotalScoreOfGrandChildren(totalScoreOfGrandChildren);
                    return userOutcomeDto;
                })
                .collect(toList());
    }
}
