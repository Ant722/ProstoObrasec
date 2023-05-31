package ru.aston.app.api_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.aston.app.api.repositories.EmployeeRepository;
import ru.aston.app.api.services.EmployeeService;
import ru.aston.model.Employee;
import ru.util.PasswordGeneratorUtils;

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
    public Employee generatePasswordByUuid(UUID uuid) {
        Employee employee = employeeRepository.findEmployeeByUuid(uuid);
        employee.setPassword(PasswordGeneratorUtils.generatePassword());
        employeeRepository.save(employee);
        log.info("Password from employee UUID {} generate", employee.getUuid());
        return employee;
    }
}
