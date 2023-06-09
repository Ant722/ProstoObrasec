package ru.aston.app.api.repositories;

import org.springframework.data.domain.Page;
import ru.aston.model.Employee;
import ru.aston.request.EmployeeSearchCriteria;

import java.util.UUID;

public interface EmployeeRepository {

    Employee findEmployeeByUuid(UUID uuid);

    Page<Employee> searchEmployeesByUsername(EmployeeSearchCriteria searchCriteria);
}
