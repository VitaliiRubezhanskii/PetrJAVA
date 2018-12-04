package com.petr.controller;

import com.petr.persistence.entity.UserMessage;
import com.petr.service.message.UserMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user/messages")
@RequiredArgsConstructor
public class UserMessageController {

    private final UserMessageService userMessageService;

    @PostMapping(value = "/send")
    public void send(@RequestBody UserMessage message){
        userMessageService.send(message);
    }

    @GetMapping
    public List<UserMessage> getAllUserMessages(){
        return userMessageService.getAllMessages();
    }

}
