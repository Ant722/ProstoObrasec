package ru.aston.app.services;

import ru.aston.model.Employee;

import java.util.UUID;

/**Service interface for Employee. Contains different operations for actions with employees.*/
public interface EmployeeService {

    void updateEmployeeInfo(Employee employee, UUID uuid);
}
