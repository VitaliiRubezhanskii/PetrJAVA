package com.petr.controller;

import com.petr.service.bank.BankService;
import com.petr.transport.dto.bank.BankCreateDto;
import com.petr.transport.dto.bank.BankFindDto;
import com.petr.transport.dto.bank.BankOutcomeDto;
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

    //admin
    @GetMapping
    public Page<BankOutcomeDto> getBanks(BankFindDto dto,
                                         @PageableDefault(size = 5) Pageable pageable) {
        return bankService.getAll(dto, pageable);
    }

    //admin
    @PutMapping
    public Long create(@RequestBody @Valid BankCreateDto dto) {
        return bankService.create(dto);
    }

    //admin
    @PostMapping("/DeletedTrue/{id}")
    public void setDeletedTrue(@PathVariable("id") Long id) {
        bankService.setDeleted(id,true);
    }

    //admin
    @PostMapping("/DeletedFalse/{id}")
    public void setDeletedFalse(@PathVariable("id") Long id) {
        bankService.setDeleted(id,false);
    }
}
