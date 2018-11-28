package com.petr.service.user;

import com.petr.persistence.entity.User;
import com.petr.transport.dto.user.UserCreateDto;
import com.petr.transport.dto.user.UserFindDto;
import com.petr.transport.dto.user.UserOutcomeDto;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

public interface UserService {
    User getById(Long id);

    void editUser(User user);

    List<User> findAll();

    Page<UserOutcomeDto> getAll(UserFindDto dto, Pageable pageable);

    Long create(UserCreateDto dto);

    void addPasswordFirstPage(MultipartFile multipartFile, Long userId);

    void addPasswordSecondPage(MultipartFile multipartFile, Long userId);

    void addPasswordLastPage(MultipartFile multipartFile, Long userId);

    void addPhotoInn(MultipartFile multipartFile, Long userId);

    void addPhoto(MultipartFile multipartFile, Long userId);

    void setVerify(boolean verify, Long userId);

    void setDeleted(boolean delete, Long userId);

    List<Long> getIdFromEntity(List<User> users);

    User findUserByUsername(String username);
}
