package com.petr.service.bank;

import com.petr.exception.BankExistsException;
import com.petr.exception.BankNotFoundException;
import com.petr.persistence.entity.Bank;
import com.petr.persistence.repository.BankRepository;
import com.petr.transport.dto.bank.BankCreateDto;
import com.petr.transport.dto.bank.BankFindDto;
import com.petr.transport.dto.bank.BankOutcomeDto;
import com.petr.transport.mapper.BankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;

    private BankMapper bankMapper;

    @Autowired
    public void setBankMapper(BankMapper bankMapper) {
        this.bankMapper = bankMapper;
    }

    @Override
    public Bank getById(Long id) {
        return bankRepository.findById(id).orElseThrow(BankNotFoundException::new);
    }

    @Override
    public boolean existsById(Long id) {
        return bankRepository.existsById(id);
    }

    @Override
    public Page<BankOutcomeDto> getAll(BankFindDto dto, Pageable pageable) {
        Page<Bank> result = bankRepository.findAll(
                BankSearchSpecification.bankFilter(dto),
                pageable
        );
        return result.map(bankMapper::toDto);
    }

    @Override
    public Long create(BankCreateDto dto) {
        validateBank(dto);
        return bankRepository.save(bankMapper.toEntity(dto)).getId();

    }

    private void validateBank(BankCreateDto createDto) {
        if (bankRepository.existsByName(createDto.getName().toLowerCase())) {
            throw new BankExistsException();
        }
    }

}
