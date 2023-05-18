package ru.aston.app.api.repositories;

import ru.aston.model.Employee;

public interface EmployeeRepository {

    Employee findEmployeeById(Long id);
}
