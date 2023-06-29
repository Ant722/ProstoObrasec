package ru.aston.repositories;

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

    Employee findEmployeeByLogin(String login);

    Employee findEmployeeByPassportId(String passportId);

    Employee save(Employee employee);

    boolean existByUuid(UUID uuid);

    boolean existByLogin(String login);

    boolean existByPassportId(String passportId);

    Page<Employee> searchEmployeesByUsername(EmployeeSearchCriteria searchCriteria);
}
