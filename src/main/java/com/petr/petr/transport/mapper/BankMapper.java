package com.petr.petr.transport.mapper;

import com.petr.petr.persistence.entity.Bank;
import com.petr.petr.service.UserService;
import com.petr.petr.transport.dto.bank.BankCreateDto;
import com.petr.petr.transport.dto.bank.BankOutcomeDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@RequiredArgsConstructor
public abstract class BankMapper {

    @Autowired
    UserService userService;

    @Mapping(target = "name", expression = "java(createDto.getName().toLowerCase())")
    public abstract Bank toEntity(BankCreateDto createDto);

    @Mapping(target = "users", expression = "java(userService.getIdFromEntity(bank.getUsers()))")
    public abstract BankOutcomeDto toDto(Bank bank);
}
