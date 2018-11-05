package com.petr.petr.service;

import com.petr.petr.persistence.entity.User;
import com.petr.petr.transport.dto.UserCreateDto;
import com.petr.petr.transport.dto.UserFindDto;
import com.petr.petr.transport.dto.UserOutcomeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    User getById(Long id);

    Page<UserOutcomeDto> getAll(UserFindDto dto, Pageable pageable);

    Long create(UserCreateDto dto);

    void addPasswordFirstPage(MultipartFile multipartFile, Long userId);

    void addPasswordSecondPage(MultipartFile multipartFile, Long userId);

    List<Long> getIdFromEntity(List<User> users);
}
