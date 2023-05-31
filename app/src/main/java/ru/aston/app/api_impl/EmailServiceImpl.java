package ru.aston.app.api_impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.aston.app.api.services.EmailService;
import ru.aston.model.Employee;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.sender.email}")
    private String emailFrom;

    private static final String MESSAGE_THEME_PASSWORD = "A new login password was created";

    private static final String PASSWORD_GENERATION_MESSAGE = "Your new password %s";


    public void sendSimpleEmail(Employee employee) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo("greek-64@mail.ru");
        message.setSubject(MESSAGE_THEME_PASSWORD);
        message.setText(String.format(PASSWORD_GENERATION_MESSAGE,employee.getPassword()));
        javaMailSender.send(message);
    }


    private String generateUserEmail() {
//TODO сгенерировать маил по требованиям

        return null;
    }
}
