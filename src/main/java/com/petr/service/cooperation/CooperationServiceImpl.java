package com.petr.service.cooperation;

import com.petr.persistence.entity.CooperationMessage;
import com.petr.persistence.repository.CooperationMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CooperationServiceImpl implements CooperationService {

    private final CooperationMessageRepository cooperationMessageRepository;

    @Override
    public void send(CooperationMessage message) {
        cooperationMessageRepository.save(message);
    }

    @Override
    public List<CooperationMessage> getAllMessages() {
       return cooperationMessageRepository.findAll();
    }
}
