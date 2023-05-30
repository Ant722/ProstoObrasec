package ru.aston.app.api.services;

import ru.aston.model.Employee;

import java.util.UUID;

public interface EmployeeService {

    Employee getEmployeeByUuid(UUID uuid);

     Employee generatePasswordByUuid(UUID uuid);
}
