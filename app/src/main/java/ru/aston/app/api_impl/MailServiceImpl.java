package ru.aston.app.api_impl;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import ru.aston.app.api.services.MailService;
import ru.aston.model.Employee;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    private final SpringTemplateEngine templateEngine;

    @Value("${spring.mail.sender.email}")
    private String emailFrom;

    @Value("${spring.mail.recipient.postfix-email}")
    private String emailPostfix;

    private static final String MESSAGE_THEME_PASSWORD = "A new login password was created";

    private static final String PASSWORD_TEMPLATE_NAME = "generate-password.html";

    @Override
    @SneakyThrows
    public void sendSimpleEmailFromGeneratePassword(Employee employee) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context thContext = new Context();
        thContext.setVariable("name", employee.getName());
        thContext.setVariable("password", employee.getPassword());
        String emailContent = templateEngine.process(PASSWORD_TEMPLATE_NAME, thContext);

        messageHelper.setFrom(emailFrom);
        messageHelper.setSubject(MESSAGE_THEME_PASSWORD);
        messageHelper.setTo(generateUserEmail(employee.getLogin()));
        messageHelper.setText(emailContent, true);
        mailSender.send(message);
        log.info("Password from employee {} sent out successfully", employee.getUuid());
    }

    private String generateUserEmail(String loginEmployee) {
        return loginEmployee + emailPostfix;
    }
}
