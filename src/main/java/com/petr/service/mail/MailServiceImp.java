package com.petr.service.mail;

import com.petr.mail.InputMailData;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:email.properties")
public class MailServiceImp implements MailService {

    @Value("${email.login}")
    private String emailLogin;

    @Value("${email.password}")
    private String emailPassword;

    @Value("${email.subject}")
    private String emailSubject;

    @Override
    public void send(InputMailData data) {
        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(emailLogin, emailPassword));
        email.setSSLOnConnect(true);
        try {
            email.setFrom(emailLogin);
            email.setSubject(emailSubject);
            email.setMsg(data.getMessage());
            email.addTo(data.getEmail());
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}