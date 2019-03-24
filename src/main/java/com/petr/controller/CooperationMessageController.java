package com.petr.controller;

import com.petr.persistence.entity.CooperationMessage;
import com.petr.persistence.entity.MessageStatus;
import com.petr.service.cooperation.CooperationService;
import com.petr.service.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cooperation")
@RequiredArgsConstructor
public class CooperationMessageController {

    private final CooperationService cooperationService;
    private final MailService mailService;

    @PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void send(@RequestBody CooperationMessage message){
        message.setStatus(MessageStatus.NEW.getValue());
        cooperationService.send(message);
    }

    @GetMapping(value = "/messages")
    public List<CooperationMessage> getAllMessages(){
       return cooperationService.getAllMessages();
    }

    @PostMapping(value = "/send-to/{mail}/approved/{isApproved}")
    public void respondOnCooperationMessage(@PathVariable("mail") String mail, @PathVariable("isApproved") boolean isApproved) throws IOException {
        mailService.send(mail, isApproved);

    }

}
