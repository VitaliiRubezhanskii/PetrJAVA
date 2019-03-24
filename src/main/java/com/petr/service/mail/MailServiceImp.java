package com.petr.service.mail;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

@Service
@PropertySource("classpath:email.properties")
public class MailServiceImp implements MailService {

    @Value("${email.login}")
    private String emailLogin;

    @Value("${email.password}")
    private String emailPassword;

    @Value("${email.subject}")
    private String emailSubject;

    @Value("${email.cooperation.approved}")
    private String approvedTextMessage;

    @Value("${email.cooperation.rejected}")
    private String rejectedTextMessage;

    @Override
    public void send(String mail, boolean isApproved) throws IOException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        Properties props = new Properties();
        URL resource = getClass().getClassLoader().getResource("email.properties");
        props.load(new InputStreamReader(resource.openStream(), "UTF8"));

        email.setAuthenticator(new DefaultAuthenticator(emailLogin, emailPassword));
        email.setSSLOnConnect(true);
        try {
            email.setFrom(emailLogin);
            email.setSubject(emailSubject);
            email.setCharset("UTF-8");
          if (isApproved) {
              email.setMsg(props.getProperty("email.cooperation.approved"));
              email.setContent(props.getProperty("email.cooperation.approved"),"text/plain; charset=UTF-8");
          } else {
              email.setMsg(props.getProperty("email.cooperation.rejected"));
              email.setContent(props.getProperty("email.cooperation.rejected"),"text/plain; charset=UTF-8");
          }
            email.addTo(mail);
            email.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}