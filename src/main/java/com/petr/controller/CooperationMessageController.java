package com.petr.controller;

import com.petr.persistence.entity.CooperationMessage;
import com.petr.persistence.entity.MessageStatus;
import com.petr.service.cooperation.CooperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cooperation")
@RequiredArgsConstructor
public class CooperationMessageController {

    private final CooperationService cooperationService;

    @PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void send(@RequestBody CooperationMessage message){
        message.setStatus(MessageStatus.NEW.getValue());
        cooperationService.send(message);
    }

    @GetMapping(value = "/messages")
    public List<CooperationMessage> getAllMessages(){
       return cooperationService.getAllMessages();
    }

}
