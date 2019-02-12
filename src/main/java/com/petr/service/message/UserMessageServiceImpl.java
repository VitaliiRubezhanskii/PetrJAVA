package com.petr.service.message;

import com.petr.persistence.entity.UserMessage;
import com.petr.persistence.repository.UserMessageRepoisitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserMessageServiceImpl implements UserMessageService {

    private final UserMessageRepoisitory userMessageRepoisitory;

    @Override
    public void send(UserMessage message) {
        userMessageRepoisitory.save(message);
    }

    @Override
    public List<UserMessage> getAllMessages() {
        return userMessageRepoisitory.findAll();
    }

    @Override
    public void delete(UserMessage message) {
        userMessageRepoisitory.delete(message);
    }
}
