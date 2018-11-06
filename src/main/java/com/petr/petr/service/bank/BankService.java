package com.petr.petr.service.bank;

import com.petr.petr.persistence.entity.Bank;
import com.petr.petr.transport.dto.bank.BankCreateDto;
import com.petr.petr.transport.dto.bank.BankFindDto;
import com.petr.petr.transport.dto.bank.BankOutcomeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BankService {

    Bank getById(Long id);

    boolean existsById(Long id);

    @Transactional(readOnly = true)
    Page<BankOutcomeDto> getAll(BankFindDto dto, Pageable pageable);

    Long create(BankCreateDto dto);
}
