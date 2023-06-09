package ru.aston.app.api.services;

import org.springframework.data.domain.Page;
import ru.aston.model.Employee;
import ru.aston.request.EmployeeSearchCriteria;

import java.util.UUID;

public interface EmployeeService {

    Employee getEmployeeByUuid(UUID uuid);

    Page<Employee> searchEmployeesByUsername(EmployeeSearchCriteria searchCriteria);
}
