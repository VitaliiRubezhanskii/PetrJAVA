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

@CrossOrigin(origins = "*", maxAge = 3600) //http://localhost:4200
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final SurveyLimitRepository surveyLimitRepository;

    //admin
//    @GetMapping(value = "/all")
//    public List<UserOutcomeDto> getUsers(UserFindDto dto, @PageableDefault(size = 5) Pageable pageable) {
//        return userService.getAll(dto, pageable).getContent();
//    }

    @GetMapping(value = "/all")
    public List<User> getUsers(){
        return userService.findAll();
    }

    @PutMapping(value = "/edit")
    public void editUser(@RequestBody User user){
        userService.editUser(user);
    }

    //user
    @PutMapping(value = "/new")
    public Long create(@RequestBody @Valid UserCreateDto dto) {
        return userService.create(dto);
    }


    //admin
    @PostMapping(value = "/verify/{isVerified}/user/{id}")
    public void setVerify(@PathVariable("id") Long id, @PathVariable("isVerified") boolean isVerified) {
        userService.setVerify(isVerified, id);
    }

    //admin
    @PutMapping(value = "/delete/{isDeleted}/user/{id}")
    public void setDeleted(@PathVariable("id") Long id, @PathVariable("isDeleted") boolean isDeleted) {
        userService.setDeleted(isDeleted, id);
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

