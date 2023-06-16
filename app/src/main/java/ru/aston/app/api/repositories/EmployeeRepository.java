package ru.aston.app.api.repositories;

import org.springframework.data.domain.Page;
import ru.aston.model.Employee;
import ru.aston.request.EmployeeSearchCriteria;

import java.util.UUID;

/**
 * Additional repository for making possible to catch some technical exceptions inside implementation
 * (like EmployeeNotFound etc).
 */
public interface EmployeeRepository {

    Employee findEmployeeByUuid(UUID uuid);

    Employee save(Employee employee);

    Employee findEmployeeByLogin(String login);

    Page<Employee> searchEmployeesByUsername(EmployeeSearchCriteria searchCriteria);
}
