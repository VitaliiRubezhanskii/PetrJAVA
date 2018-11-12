package com.petr.transport.mapper;

import com.petr.persistence.entity.Bank;
import com.petr.service.user.UserService;
import com.petr.transport.dto.bank.BankCreateDto;
import com.petr.transport.dto.bank.BankOutcomeDto;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class BankMapper {

    @Autowired
    UserService userService;

    @Mapping(target = "name", expression = "java(createDto.getName().toLowerCase())")
    public abstract Bank toEntity(BankCreateDto createDto);

    @Mapping(target = "users", expression = "java(userService.getIdFromEntity(bank.getUsers()))")
    public abstract BankOutcomeDto toDto(Bank bank);
}
