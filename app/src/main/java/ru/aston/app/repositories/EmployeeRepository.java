package ru.aston.app.repositories;

import ru.aston.model.Employee;

import java.util.UUID;

/**Additional repository for making possible to catch some technical exceptions inside implementation.*/
public interface EmployeeRepository {

    Employee findEmployeeById(Long id);

    Employee findEmployeeByUuid(UUID uuid);

    Employee save(Employee employee);
}
