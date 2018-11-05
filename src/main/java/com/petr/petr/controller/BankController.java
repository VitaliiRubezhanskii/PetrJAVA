package com.petr.petr.controller;

import com.petr.petr.service.BankService;
import com.petr.petr.transport.dto.bank.BankCreateDto;
import com.petr.petr.transport.dto.bank.BankFindDto;
import com.petr.petr.transport.dto.bank.BankOutcomeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/banks")
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;

    @GetMapping
    public Page<BankOutcomeDto> getSchools(BankFindDto dto,
                                           @PageableDefault(size = 5) Pageable pageable) {
        return bankService.getAll(dto, pageable);
    }

    @PutMapping
    public Long create(@RequestBody @Valid BankCreateDto dto){
        return bankService.create(dto);
    }
}
