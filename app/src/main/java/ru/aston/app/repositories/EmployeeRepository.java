package ru.aston.app.repositories;

//import ru.aston.model.Employee;

import ru.aston.model.Employee;

import java.util.UUID;

public interface EmployeeRepository {

    Employee findEmployeeByUuid(UUID uuid);

    Employee findEmployeeByLogin(String login);
}
