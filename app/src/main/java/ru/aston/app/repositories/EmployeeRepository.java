package ru.aston.app.repositories;

//import ru.aston.model.Employee;

import ru.aston.model.Employee;

import java.util.UUID;

public interface EmployeeRepository {

    Employee findEmployeeById(Long id);

    Employee findEmployeeByUuid(UUID uuid);

    Employee save(Employee employee);
}
