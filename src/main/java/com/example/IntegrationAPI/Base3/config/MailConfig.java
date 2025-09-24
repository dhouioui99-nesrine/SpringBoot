package com.example.IntegrationAPI.Base3.config;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
@Transactional
@Service
public class MailConfig {
    @Autowired
    private JavaMailSender mailSender;


    @Async
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }
}
