package com.petr.controller;

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

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    //admin
    @GetMapping
    public Page<UserOutcomeDto> getUsers(UserFindDto dto, @PageableDefault(size = 5) Pageable pageable) {
        return userService.getAll(dto, pageable);
    }

    //admin
    @PostMapping(value = "/verify/{id}/{verify}")
    public void setVerify(@PathVariable("id") Long id,
                          @PathVariable("verify") boolean verify) {
        userService.setVerify(verify, id);
    }

    //admin
    @PostMapping(value = "/deleted/{id}/{deleted}")
    public void setDeleted(@PathVariable("id") Long id,
                           @PathVariable("deleted") boolean verify) {
        userService.setDeleted(verify, id);
    }

    //user
    @PutMapping
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


}

