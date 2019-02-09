package com.petr.service.user;

import com.petr.exception.bank.BankDeletedException;
import com.petr.exception.user.*;
import com.petr.persistence.entity.Address;
import com.petr.persistence.entity.DocumentType;
import com.petr.persistence.entity.User;
import com.petr.persistence.repository.UserRepository;
import com.petr.service.bank.BankService;
import com.petr.transport.dto.user.UserCreateDto;
import com.petr.transport.dto.user.UserFindDto;
import com.petr.transport.dto.user.UserOutcomeDto;

import com.petr.transport.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;

@Service("userService")
public class UserServiceImpl extends UserSearchSpecification implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankService bankService;

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
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<UserOutcomeDto> getAll(UserFindDto dto, Pageable pageable) {
        Page<User> result = userRepository.findAll(
                userFilter(dto),
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
    public void uploadFiles(Long userId, HttpServletRequest request) {
        MultipartHttpServletRequest multiPartRequest;
        List<MultipartFile> files = new ArrayList<>();
        try {
            multiPartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> itr = multiPartRequest.getFileNames();

            while (itr.hasNext()) {
                MultipartFile mFile = multiPartRequest.getFile(itr.next());
                System.out.println("FileName is " + mFile.getOriginalFilename() + "for user id=" + userId);
                files.add(mFile);
            }
            addPhoto(files,userId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downloadFiles(Long userId, String type, HttpServletResponse response) {
        User user = getById(userId);
        String pathToDocument="";
        switch (type){
            case "photo":
                pathToDocument = user.getPhoto();
                break;
            case "inn":
                pathToDocument = user.getPhotoInn();
                break;
            case "pass_first":
                pathToDocument = user.getPasswordFirstPage();
                break;
            case "pass_second":
                pathToDocument = user.getPasswordSecondPage();
                break;
            case "pass_last":
                pathToDocument = user.getPasswordLastPage();
                break;
        }
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Files.readAllBytes(Paths.get(pathToDocument)));
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +user.getUsername()+ ".jpg");
            StreamUtils.copy(inputStream, response.getOutputStream());
        }catch (IOException ex){
            ex.printStackTrace();
        }
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
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

    @Override
    public User findUserByUsername(String email) {
        return userRepository.findUserByUsername(email);
    }

    @Override
    public void editUser(User user) {
        User editedUser = getById(user.getId());
        System.out.println(user.getMiddleName());
        editedUser.setMiddleName(user.getMiddleName());
        Address address = new Address();
        address.setOblast(user.getAddress().getOblast());
        address.setCity(user.getAddress().getCity());
        address.setStreet(user.getAddress().getStreet());
        address.setBuildingNum(user.getAddress().getBuildingNum());
        address.setApartmentNum(user.getAddress().getApartmentNum());

        editedUser.setAddress(address);
        editedUser.setPhone(user.getPhone());
        editedUser.setEmail(user.getEmail());
        userRepository.save(editedUser);
    }

    private String savePhoto(MultipartFile multipartFile, String username) {
        String photoPath=null;
        try {
            Files.createDirectories(Paths.get(new File("./img2/" + username).getAbsolutePath()));
            byte[] bytes = multipartFile.getBytes();
             photoPath = Paths.get(new File("img2/" + username).getAbsolutePath())
                    + "/"
                    + String.valueOf(Instant.now().getEpochSecond())
                    + multipartFile.getOriginalFilename();

            Path path = Paths.get(photoPath);
            Files.write(path, bytes);

        }
             catch (IOException e) {
                e.printStackTrace();
            }

        return photoPath;
    }

    private void addPhoto(List<MultipartFile> multipartFiles, Long userId) {
        User user = getById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        if (user.getPhoto() != null) {
            throw new PhotoException();
        }
        user.setPhoto(savePhoto(multipartFiles.get(0), user.getUsername()));
        user.setPhotoInn(savePhoto(multipartFiles.get(1), user.getUsername()));
        user.setPasswordFirstPage(savePhoto(multipartFiles.get(2), user.getUsername()));
        user.setPasswordSecondPage(savePhoto(multipartFiles.get(3), user.getUsername()));
        user.setPasswordLastPage(savePhoto(multipartFiles.get(4), user.getUsername()));
        userRepository.save(user);

    }

    private void validateUser(UserCreateDto createDto) {
        if (bankService.getById(createDto.getBank()).isDeleted()){
            throw new BankDeletedException();
        }
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

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    @Override
    public List<User> firndUsersByParentId(Long parentId) {
        return userRepository.findUsersByParentId(getById(parentId));
    }
}
