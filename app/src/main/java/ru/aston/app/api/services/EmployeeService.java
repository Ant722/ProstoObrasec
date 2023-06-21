package ru.aston.app.api.services;

import ru.aston.model.Employee;

import java.util.UUID;

/**
 * Service interface for Employee. Contains different operations for actions with employees.
 */
public interface EmployeeService {

    Employee getEmployeeByUuid(UUID uuid);

    String createNewEmployee(Employee employee);

    void updateEmployeeInfo(Employee employee, UUID uuid);

     Employee generatePasswordByUuid(UUID uuid);
}
