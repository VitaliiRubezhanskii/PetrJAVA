package com.petr.controller;

import com.petr.persistence.entity.User;
import com.petr.persistence.repository.SurveyLimitRepository;
import com.petr.service.user.UserService;
import com.petr.transport.dto.user.UserCreateDto;
import com.petr.transport.dto.user.UserFindDto;
import com.petr.transport.dto.user.UserOutcomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final SurveyLimitRepository surveyLimitRepository;

    //admin
    @GetMapping(value = "/all")
    public List<UserOutcomeDto> getUsers(UserFindDto dto, @PageableDefault(size = 5) Pageable pageable) {
        return userService.getAll(dto, pageable).getContent();
    }

    //admin
    @PostMapping(value = "/verifyTrue/{id}")
    public void setVerifyTrue(@PathVariable("id") Long id) {
        userService.setVerify(true, id);
    }

    //admin
    @PostMapping(value = "/verifyFalse/{id}")
    public void setVerifyFalse(@PathVariable("id") Long id) {
        userService.setVerify(false, id);
    }

    //admin
    @PostMapping(value = "/deletedTrue/{id}")
    public void setDeletedTrue(@PathVariable("id") Long id) {
        userService.setDeleted(true, id);
    }

    //admin
    @PostMapping(value = "/deletedFalse/{id}")
    public void setDeletedFalse(@PathVariable("id") Long id) {
        userService.setDeleted(false, id);
    }

    //user
    @PutMapping(value = "/new")
    public Long create(@RequestBody @Valid UserCreateDto dto) {
        return userService.create(dto);
    }

    //user
    @PostMapping(value = "/addPasswordFirstPage/{id}")
    public void addFirstPhoto(@RequestParam("file") MultipartFile file,
                              @PathVariable("id") Long id) {
        userService.addPasswordFirstPage(file, id);
    }

    //user
    @PostMapping(value = "/addPasswordSecondPage/{id}")
    public void addSecondPhoto(@RequestParam("file") MultipartFile file,
                               @PathVariable("id") Long id) {
        userService.addPasswordSecondPage(file, id);
    }

    //user
    @PostMapping(value = "/addPasswordLastPage/{id}")
    public void addLastPhoto(@RequestParam("file") MultipartFile file,
                             @PathVariable("id") Long id) {
        userService.addPasswordLastPage(file, id);
    }

    //user
    @PostMapping(value = "/addPhotoInn/{id}")
    public void addPhotoInn(@RequestParam("file") MultipartFile file,
                            @PathVariable("id") Long id) {
        userService.addPhotoInn(file, id);
    }

    //user
    @PostMapping(value = "/addPhoto/{id}")
    public void addPhoto(@RequestParam("file") MultipartFile file,
                         @PathVariable("id") Long id) {
        userService.addPhoto(file, id);
    }

    @GetMapping(value = "/{username}")
    public User getUserByUserName(@PathVariable String username){
        return userService.findUserByUsername(username);
    }
}

