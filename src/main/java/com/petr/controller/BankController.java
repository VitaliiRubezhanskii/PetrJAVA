package com.petr.controller;

import com.petr.persistence.entity.Bank;
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
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
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

    @GetMapping(value = "/all")
    public List<Bank> getAllBanks(){
        return bankService.getAllBanks();
    }

    //admin
    @PutMapping
    public Long create(@RequestBody @Valid BankCreateDto dto) {
        return bankService.create(dto);
    }

    //admin
    @PostMapping("/deleted/{isDeleted}/bank/{id}")
    public void setDeletedTrue(@PathVariable("id") Long id,@PathVariable("isDeleted") boolean isDeleted) {
        bankService.setDeleted(id, isDeleted);
    }

    @GetMapping(value = "/bank/{bankName}")
    public Bank findBankByName(@PathVariable("bankName") String bankName){
        return  bankService.findBankByName(bankName);
    }

}
