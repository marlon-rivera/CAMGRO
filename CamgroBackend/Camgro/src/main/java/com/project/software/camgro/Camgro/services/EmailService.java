package com.project.software.camgro.Camgro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String toEmail, String subject, String body){
        System.out.println(toEmail + " - " + subject + " - " + body);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setText("Tu codigo de recuperacion es 9090");
        mailMessage.setSubject(subject);
        System.out.println("Se seteo bien");
        javaMailSender.send(mailMessage);
        System.out.println("Funciono bien");
    }

}
