package com.example.demo;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendWelcomeEmail(String toEmail, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome to Student Manager! 🎓");
        message.setText(
            "Hello " + name + ",\n\n" +
            "Welcome to Student Manager!\n\n" +
            "Your account has been successfully created.\n\n" +
            "Best regards,\n" +
            "Student Manager Team"
        );
        mailSender.send(message);
    }
}