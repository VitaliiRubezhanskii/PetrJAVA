package com.petr.service.bank;

import com.petr.persistence.entity.Bank;
import com.petr.transport.dto.bank.BankCreateDto;
import com.petr.transport.dto.bank.BankFindDto;
import com.petr.transport.dto.bank.BankOutcomeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BankService {

    Bank getById(Long id);

    List<Bank> getAllBanks();

    boolean existsById(Long id);

    Bank findBankByName(String bankName);

    @Transactional(readOnly = true)
    Page<BankOutcomeDto> getAll(BankFindDto dto, Pageable pageable);

    Long create(BankCreateDto dto);

    void setDeleted(Long id, boolean deleted);


}

