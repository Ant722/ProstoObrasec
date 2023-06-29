package ru.aston.services;

import org.springframework.data.domain.Page;
import ru.aston.model.Employee;
import ru.aston.request.EmployeeSearchCriteria;

import java.util.UUID;

/**
 * Service interface for Employee. Contains different operations for actions with employees.
 */
public interface EmployeeService {

    Employee getEmployeeByUuid(UUID uuid);

    Employee generatePasswordByUuid(UUID uuid);

    String createNewEmployee(Employee employee);

    void updateEmployeeInfo(Employee employee, UUID uuid);

    Page<Employee> searchEmployeesByUsername(EmployeeSearchCriteria searchCriteria);

    Employee checkEmployeeByLoginAndPassword(String login, String Password);
}
