package com.petr.service.surveyResult;

import com.petr.persistence.entity.SurveyResult;
import com.petr.persistence.entity.User;
import com.petr.persistence.repository.SurveyResultRepository;
import com.petr.service.user.UserService;
import com.petr.transport.dto.surveyResult.SurveyResultHierarchy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class SurveyResultServiceImpl implements SurveyResultService {

    private final SurveyResultRepository surveyResultRepository;
    private final UserService userService;

    @Autowired
    public SurveyResultServiceImpl(SurveyResultRepository surveyResultRepository, UserService userService) {
        this.surveyResultRepository = surveyResultRepository;
        this.userService = userService;
    }

    @Override
    public void submit(SurveyResult survey) {
        surveyResultRepository.save(survey);
    }

    @Override
    public List<SurveyResult> getSurveyResultByUser(User user) {
        return surveyResultRepository.getSurveyResultByUser(user);
    }

    @Override
    public List<SurveyResult> getSurveyResultOfAll() {
        return surveyResultRepository.findAll();
    }

//    @Override
//    public List<LowLevelHierarchy> getSurveyResultHierarchy() {
//        List<SurveyResult> allResults = getSurveyResultOfAll();
//        LowLevelHierarchy hierarchy = new LowLevelHierarchy();
//        List<SurveyResultHierarchy> embeddedHierarchyList = new ArrayList<>();
//        List<LowLevelHierarchy> mainHierarchy= new ArrayList<>();
//
//        for (SurveyResult result : allResults){
//            if (result.getUser().getParentId() == null) {
//                hierarchy.setSurveyResult(result);
//                List<SurveyResult> secondLevelResults = userService.findUsersByParentId(result.getUser().getId())
//                        .stream()
//                        .map(user->getSurveyResultByUser(user))
//                        .flatMap(s ->s.stream())
//                        .collect(Collectors.toList());
//               for (SurveyResult secondLevelResult : secondLevelResults){
//                   SurveyResultHierarchy embeddedElement = new SurveyResultHierarchy();
//                   embeddedElement.setSurveyResult(secondLevelResult);
//                   List<SurveyResult> thirdLevelResults = userService.findUsersByParentId(secondLevelResult.getUser().getId())
//                           .stream()
//                           .map(user -> getSurveyResultByUser(user))
//                           .flatMap(s->s.stream())
//                           .collect(Collectors.toList());
//                   embeddedElement.setEmbeddedResults(thirdLevelResults);
//                   embeddedHierarchyList.add(embeddedElement);
//               }
//                hierarchy.setLowLevelHierarchies(embeddedHierarchyList);
//               mainHierarchy.add(hierarchy);
//
//            }
//
//        }
//        return mainHierarchy;
//    }
}
