package com.petr.service.cooperation;

import com.petr.persistence.entity.CooperationMessage;

import java.util.List;

public interface CooperationService {

    void send(CooperationMessage message);
   List<CooperationMessage> getAllMessages();

}
