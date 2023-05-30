package ru.aston.app.api.repositories;

import org.springframework.data.domain.Page;
import ru.aston.model.Employee;

import java.util.UUID;

public interface EmployeeRepository {

    Employee findEmployeeByUuid(UUID uuid);

    Page<Employee> searchEmployeesByUsername(String status, String role, String sort, String surname, Integer page);
}
