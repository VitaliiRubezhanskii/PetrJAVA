package com.petr.service.user;


import com.petr.persistence.entity.User;
import com.petr.transport.dto.user.UserCreateDto;
import com.petr.transport.dto.user.UserFindDto;
import com.petr.transport.dto.user.UserOutcomeDto;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    User getById(Long id);

    void editUser(User user);

    List<User> findAll();

    List<UserOutcomeDto> getAll();

    Long create(UserCreateDto dto);

    void uploadFiles(Long userId, HttpServletRequest request);

    ResponseEntity<InputStreamResource> downloadFiles(Long userId, String type, HttpServletResponse response);

    void setVerify(boolean verify, Long userId);

    void setDeleted(boolean delete, Long userId);

    List<Long> getIdFromEntity(List<User> users);

    User findUserByUsername(String username);

    List<User> findUsersByParentId(Long parentId);
}
