package ru.aston.app.api.repositories;

import ru.aston.model.Employee;

import java.util.UUID;

public interface EmployeeRepository {

    Employee findEmployeeByUuid(UUID uuid);

    void save(Employee employee);
}
