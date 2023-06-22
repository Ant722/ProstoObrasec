package ru.aston.app.api.services;

import ru.aston.model.Employee;

public interface MailService {

    void sendSimpleEmailFromGeneratePassword(Employee employee);
}
