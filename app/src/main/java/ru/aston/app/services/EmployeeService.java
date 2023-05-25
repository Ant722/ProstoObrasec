package ru.aston.app.services;

import ru.aston.model.Employee;

import java.util.UUID;

public interface EmployeeService {

    void updateEmployeeInfo(Employee employee, UUID uuidForCheckAdmin, Long employeeId);
}
