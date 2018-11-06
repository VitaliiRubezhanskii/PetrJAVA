package com.petr.petr.service.user;

import com.petr.petr.exception.*;
import com.petr.petr.persistence.entity.User;
import com.petr.petr.persistence.repository.UserRepository;
import com.petr.petr.transport.dto.user.UserCreateDto;
import com.petr.petr.transport.dto.user.UserFindDto;
import com.petr.petr.transport.dto.user.UserOutcomeDto;
import com.petr.petr.transport.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getById(Long id) {
        if (id == null) {
            return null;
        }
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Page<UserOutcomeDto> getAll(UserFindDto dto, Pageable pageable) {
        Page<User> result = userRepository.findAll(
                UserSearchSpecification.userFilter(dto),
                pageable
        );
        return result.map(userMapper::toDto);
    }

    @Override
    public Long create(UserCreateDto dto) {
        validateUser(dto);
        return userRepository.save(userMapper.toEntity(dto)).getId();

    }

    @Override
    public void addPasswordFirstPage(MultipartFile multipartFile, Long userId) {
        User user = getById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        if (user.getPasswordFirstPage() != null) {
            throw new PassportFistPageException();
        }
        String photoPath = savePhoto(multipartFile, user);
        user.setPasswordFirstPage(photoPath);
        userRepository.save(user);
    }

    @Override
    public void addPasswordSecondPage(MultipartFile multipartFile, Long userId) {
        User user = getById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        if (user.getPasswordSecondPage() != null) {
            throw new PassportSecondPageException();
        }
        String photoPath = savePhoto(multipartFile, user);
        user.setPasswordSecondPage(photoPath);
        userRepository.save(user);
    }

    @Override
    public void addPasswordLastPage(MultipartFile multipartFile, Long userId) {
        User user = getById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        if (user.getPasswordLastPage() != null) {
            throw new PassportLastPageException();
        }
        String photoPath = savePhoto(multipartFile, user);
        user.setPasswordLastPage(photoPath);
        userRepository.save(user);
    }

    @Override
    public void addPhotoInn(MultipartFile multipartFile, Long userId) {
        User user = getById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        if (user.getPhotoInn() != null) {
            throw new PhotoInnException();
        }
        String photoPath = savePhoto(multipartFile, user);
        user.setPhotoInn(photoPath);
        userRepository.save(user);
    }

    @Override
    public void addPhoto(MultipartFile multipartFile, Long userId) {
        User user = getById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        if (user.getPhoto() != null) {
            throw new PhotoException();
        }
        String photoPath = savePhoto(multipartFile, user);
        user.setPhoto(photoPath);
        userRepository.save(user);
    }

    @Override
    public void setVerify(boolean verify, Long userId) {
        User user = getById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        user.setVerify(verify);
        userRepository.save(user);
    }

    @Override
    public void setDeleted(boolean delete, Long userId) {
        User user = getById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        user.setDeleted(delete);
        userRepository.save(user);
    }

    private String savePhoto(MultipartFile multipartFile, User user) {
        String photoPath = null;
        try {
            Files.createDirectories(Paths.get("/home/user/img2/" + user.getId()));
            byte[] bytes = multipartFile.getBytes();
            photoPath = "/home/user/img2/"
                    + user.getId().toString()
                    + "/"
                    + Instant.now().getEpochSecond()
                    + multipartFile.getOriginalFilename();

            Path path = Paths.get(photoPath);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return photoPath;
    }

    private void validateUser(UserCreateDto createDto) {
        if (userRepository.existsByEmail(createDto.getEmail())) {
            throw new UserEmailExistsException();
        }
        if (userRepository.existsByInn(createDto.getInn().toLowerCase())) {
            throw new UserINNExistsException();
        }
        if (userRepository.existsByPassport(createDto.getPassport().toLowerCase())) {
            throw new UserPassportExistsException();
        }
        if (userRepository.existsByPhone(createDto.getPhone().toLowerCase())) {
            throw new UserPhoneExistsException();
        }
    }


    @Override
    public List<Long> getIdFromEntity(List<User> users) {
        if (users == null) {
            return null;
        }
        List<Long> userIds = new ArrayList<>();
        for (User user : users) {
            userIds.add(user.getId());
        }
        return userIds;
    }


}
