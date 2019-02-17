package com.petr.transport.mapper;

import com.petr.persistence.entity.User;
import com.petr.service.bank.BankService;
import com.petr.service.user.UserService;
import com.petr.transport.dto.user.UserCreateDto;
import com.petr.transport.dto.user.UserOutcomeDto;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

    @Autowired
    BankService bankService;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Mappings({
            @Mapping(target = "bank",
                    expression = "java(bankService.getById(createDto.getBank()))"),
            @Mapping(target = "parentId",
                    expression = "java(userService.getById(createDto.getParentId()))"),
            @Mapping(target = "passport",
                    expression = "java(createDto.getPassport().toLowerCase())"),
            @Mapping(target = "phone",
                    expression = "java(createDto.getPhone().toLowerCase())"),
            @Mapping(target = "name",
                    expression = "java(createDto.getName().toLowerCase())"),
            @Mapping(target = "surname",
                    expression = "java(createDto.getSurname().toLowerCase())"),
            @Mapping(target = "middleName",
                    expression = "java(createDto.getMiddleName().toLowerCase())"),
            @Mapping(target = "inn",
                    expression = "java(createDto.getInn().toLowerCase())"),
            @Mapping(target = "password",
                    expression = "java(passwordEncoder.encode(createDto.getPassword()))"),
            @Mapping(target = "username",
                    expression = "java(createDto.getUsername().toLowerCase())"),
            @Mapping(target = "roles",
                    expression = "java(createDto.getRoles())")

    })
    public abstract User toEntity(UserCreateDto createDto);

    @Mappings({
            @Mapping(target = "bank",
                    expression = "java(user.getBank().getId())"),
            @Mapping(target = "parentId",
                    expression = "java(user.getParentId()==null?null:user.getParentId().getId())"),
            @Mapping(target = "oblast",
                    expression = "java(user.getAddress().getOblast())"),
            @Mapping(target = "city",
                    expression = "java(user.getAddress().getCity())"),
            @Mapping(target = "street",
                    expression = "java(user.getAddress().getStreet())"),
            @Mapping(target = "building",
                    expression = "java(user.getAddress().getBuildingNum())"),
            @Mapping(target = "apartment",
                    expression = "java(user.getAddress().getApartmentNum())")
    })

    public abstract UserOutcomeDto toDto(User user);
}
