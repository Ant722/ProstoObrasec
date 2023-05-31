package ru.aston.app.api.services;

import ru.aston.model.Employee;

public interface EmailService {

    void sendSimpleEmail(Employee email);
}
