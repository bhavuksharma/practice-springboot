package com.letuslearn.fieldprofilier.config.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender theMailSender){
        this.mailSender = theMailSender;
    }

    public void sendReport(String to, String subject, String body, File file) throws MessagingException{
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);
        helper.addAttachment(file.getName(), new FileSystemResource(file));

        mailSender.send(message);
        System.out.println("Email sent to: "+ to + " with attachment Json file name: "+ file.getName());
    }
}
