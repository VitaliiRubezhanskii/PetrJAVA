package com.petr.service.message;

import com.petr.persistence.entity.UserMessage;

import java.util.List;

public interface UserMessageService {

    void send(UserMessage message);
    List<UserMessage> getAllMessages();
}
