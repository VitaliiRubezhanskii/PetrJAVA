package com.petr.controller;

import com.petr.persistence.entity.DocumentType;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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

    @PostMapping(value = "/user/{id}/document")
    public void uploadFiles(@PathVariable("id") Long id, HttpServletRequest request){
        userService.uploadFiles(id, request);
    }

    @GetMapping(value = "/user/{id}/document")
    public ResponseEntity<byte []> downloadFile(@PathVariable("id") Long id){
        User user=userService.getById(id);
        File file = new File("/home/vitalii/Downloads/photoofmine.jpg");

        try {
            FileInputStream inputStream=new FileInputStream(file);

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        }catch (IOException ex){ex.printStackTrace();}
        return  ResponseEntity.status(404).body(null);
    }

    @GetMapping(value = "/{username}")
    public User getUserByUserName(@PathVariable String username){
        return userService.findUserByUsername(username);
    }
}

