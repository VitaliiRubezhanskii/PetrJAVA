package com.petr.petr.controller;

import com.petr.petr.service.UserService;
import com.petr.petr.transport.dto.UserCreateDto;
import com.petr.petr.transport.dto.UserFindDto;
import com.petr.petr.transport.dto.UserOutcomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @GetMapping
    public Page<UserOutcomeDto> getSchools(UserFindDto dto,
                                           @PageableDefault(size = 5) Pageable pageable) {
        return userService.getAll(dto, pageable);
    }


    @PutMapping
    public Long create(@RequestBody @Valid UserCreateDto dto) {
        return userService.create(dto);
    }

    @PostMapping(value = "/addPasswordFirstPage/{id}")
    public void addFirstPhoto(@RequestParam("file") MultipartFile passwordFirstPage,
                              @PathVariable("id") Long id) {
        userService.addPasswordFirstPage(passwordFirstPage, id);
    }

    @PostMapping(value = "/addPasswordSecondPage/{id}")
    public void addSecondPhoto(@RequestParam("file") MultipartFile passwordFirstPage,
                              @PathVariable("id") Long id) {
        userService.addPasswordSecondPage(passwordFirstPage, id);
    }


}

