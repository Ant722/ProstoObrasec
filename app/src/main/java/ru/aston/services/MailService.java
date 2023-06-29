package ru.aston.services;

import ru.aston.model.Employee;

public interface MailService {

    void sendSimpleEmailFromGeneratePassword(Employee employee);
}
