package com.petr.service.mail;

import com.petr.mail.InputMailData;

import java.io.IOException;

public interface MailService {

    void send(String mail, boolean isApproved) throws IOException;
}
