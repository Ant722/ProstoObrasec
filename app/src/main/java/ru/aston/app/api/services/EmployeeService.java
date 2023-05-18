package ru.aston.app.api.services;

import ru.aston.model.Employee;

public interface EmployeeService {

    Employee getEmployeeById(Long id);
}
