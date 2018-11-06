package com.petr.petr.transport.mapper;

import com.petr.petr.persistence.entity.User;
import com.petr.petr.service.bank.BankService;
import com.petr.petr.service.user.UserService;
import com.petr.petr.transport.dto.user.UserCreateDto;
import com.petr.petr.transport.dto.user.UserOutcomeDto;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

    @Autowired
    BankService bankService;

    @Autowired
    UserService userService;

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
            @Mapping(target = "patronymic",
                    expression = "java(createDto.getPatronymic().toLowerCase())"),
            @Mapping(target = "inn",
                    expression = "java(createDto.getInn().toLowerCase())")
    })
    public abstract User toEntity(UserCreateDto createDto);

    @Mappings({
            @Mapping(target = "bank",
                    expression = "java(user.getBank().getId())"),
            @Mapping(target = "parentId",
                    expression = "java(user.getParentId()==null?null:user.getParentId().getId())")
    })

    public abstract UserOutcomeDto toDto(User user);
}
