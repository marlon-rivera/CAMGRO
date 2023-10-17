package com.project.software.camgro.Camgro.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    public void sendEmailRecoverPassword(String toEmail, String code){
        String subject = "Recuperación de contraseña: Acción requerida para tu cuenta en CAMGRO";
        String body = "¡Hola!\n" +
                "\n" +
                "Somos del equipo de CAMGRO y estamos comprometidos con garantizar la seguridad de tu cuenta. Hemos notado que has tenido problemas para acceder a tu cuenta y estamos aquí para ayudarte a recuperarla.\n" +
                "\n" +
                "A continuación, encontrarás un código especial que te permitirá restablecer tu contraseña y recuperar el acceso a tu cuenta:\n" +
                "\n" +
                "[Código de recuperación: ****]\n" +
                "\n" +
                "Por favor, ten en cuenta que este código es válido únicamente durante los próximos 10 minutos. Si pasara este tiempo, te pedimos que generes un nuevo código para continuar con el proceso de recuperación.\n" +
                "\n" +
                "Si tienes alguna pregunta o necesitas asistencia adicional, no dudes en contactarnos. Estamos aquí para brindarte todo el apoyo que necesites.\n" +
                "\n" +
                "Saludos cordiales,\n" +
                "\n" +
                "Equipo CAMGRO";
        body = body.replace("****", code);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setText(body);
        mailMessage.setSubject(subject);
        javaMailSender.send(mailMessage);
    }

}
