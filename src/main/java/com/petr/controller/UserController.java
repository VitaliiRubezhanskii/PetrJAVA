package com.petr.controller;

import com.petr.persistence.entity.DocumentType;
import com.petr.persistence.entity.User;
import com.petr.persistence.repository.SurveyLimitRepository;
import com.petr.service.user.UserService;
import com.petr.transport.dto.user.UserCreateDto;
import com.petr.transport.dto.user.UserFindDto;
import com.petr.transport.dto.user.UserOutcomeDto;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
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

    @GetMapping(value = "/user/{id}/document" , produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public void downloadFile(@PathVariable("id") Long id,  HttpServletResponse response) {
        User user = userService.getById(id);
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Files.readAllBytes(Paths.get(user.getPhoto())));
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +user.getUsername()+ ".jpg");
            StreamUtils.copy(inputStream, response.getOutputStream());
        }catch (IOException ex){ex.printStackTrace();}

    }

    @GetMapping(value = "/{username}")
    public User getUserByUserName(@PathVariable String username){
        return userService.findUserByUsername(username);
    }
}

