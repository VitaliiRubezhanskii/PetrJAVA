package com.petr.controller;

import com.petr.mail.InputMailData;
import com.petr.service.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

//    @PostMapping("/send")
//    public void sendEmail(@RequestBody @Valid InputMailData data
//    ) {
//        mailService.send(data);
//    }
}