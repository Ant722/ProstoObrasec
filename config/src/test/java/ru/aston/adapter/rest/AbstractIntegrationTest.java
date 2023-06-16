package ru.aston.adapter.rest;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Transactional
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        private PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:14.2");;

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            postgres.start();
            System.setProperty("SPRING_DATASOURCE_URL", postgres.getJdbcUrl());
            System.setProperty("SPRING_DATASOURCE_USERNAME", postgres.getUsername());
            System.setProperty("SPRING_DATASOURCE_PASSWORD", postgres.getPassword());
            System.setProperty("SPRING_MAIL_HOST", "SPRING_MAIL_HOST_STUB");
            System.setProperty("SPRING_MAIL_PORT", "1000");
            System.setProperty("SPRING_MAIL_EMAIL", "SPRING_MAIL_EMAIL_STUB");
            System.setProperty("SPRING_MAIL_PASSWORD", "SPRING_MAIL_PASSWORD_STUB");
            System.setProperty("SPRING_MAIL_EMAIL_SENDER", "SPRING_MAIL_EMAIL_SENDER_STUB");
            System.setProperty("SPRING_MAIL_EMAIL_POSTFIX", "SPRING_MAIL_EMAIL_POSTFIX_STUB");
        }
    }
}
