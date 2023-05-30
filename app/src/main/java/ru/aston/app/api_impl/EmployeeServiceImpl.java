package ru.aston.app.api_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.aston.app.api.repositories.EmployeeRepository;
import ru.aston.app.api.services.EmployeeService;
import ru.aston.model.Employee;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeByUuid(UUID uuid) {
        Employee employee = employeeRepository.findEmployeeByUuid(uuid);
        log.info("Taken from employee with UUID {}", employee.getUuid());
        return employee;
    }

    @Override
    public Page<Employee> searchEmployeesByUsername(
            String status,
            String role,
            String sort,
            String surname,
            Integer page) {
        return employeeRepository.searchEmployeesByUsername(status, role, sort, surname, page);
    }
}
