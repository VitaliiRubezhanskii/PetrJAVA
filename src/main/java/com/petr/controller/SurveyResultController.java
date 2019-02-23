package com.petr.controller;

import com.petr.persistence.entity.SurveyResult;
import com.petr.persistence.entity.User;
import com.petr.security.model.Role;
import com.petr.service.surveyResult.SurveyResultService;
import com.petr.service.user.UserService;
import com.petr.transport.dto.surveyResult.SurveyUserResultDto;
import com.petr.transport.dto.user.UserOutcomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/scoring")
@RequiredArgsConstructor
public class SurveyResultController {

    private final SurveyResultService surveyResultService;
    private final UserService userService;

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

    @GetMapping(value = "/users/responses")
    public List<SurveyUserResultDto> getAllSurveyUserResults(){
    return surveyResultService.getAllSurveyUserResults();
    }

    @GetMapping(value = "/users")
    public List<UserOutcomeDto> getSurveyResults(){
       return surveyResultService.getSurveyResults();
    }
}
