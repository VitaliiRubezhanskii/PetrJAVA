package com.petr.controller;


import com.petr.persistence.entity.User;
import com.petr.persistence.repository.SurveyLimitRepository;
import com.petr.service.user.UserService;
import com.petr.transport.dto.user.UserCreateDto;
import com.petr.transport.dto.user.UserFindDto;
import com.petr.transport.dto.user.UserOutcomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
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

    @GetMapping(value = "/all/structure")
    public List<UserOutcomeDto> getUsersStructure(){
         return userService.getAll();
    }

    @GetMapping(value = "/all")
    public List<User> getUsers() {
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

    @PostMapping(value = "/user/{id}/document")
    public void uploadFiles(@PathVariable("id") Long id, HttpServletRequest request){
        userService.uploadFiles(id, request);
    }

    @GetMapping(value = "/user/{id}/document/{type}" , produces = {MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("id") Long id,
                                       @PathVariable("type") String type, HttpServletResponse response) {
       return userService.downloadFiles(id, type, response);

    }

    @GetMapping(value = "/{username}")
    public User getUserByUserName(@PathVariable String username){
        return userService.findUserByUsername(username);
    }
}

