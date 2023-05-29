package ru.aston.jpa.tech_adapter_impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.aston.app.repositories.EmployeeRepository;
import ru.aston.exception.EmployeeNotFoundException;
import ru.aston.jpa.repositories.EmployeeJpaRepository;
import ru.aston.model.Employee;

import java.util.UUID;

/**EmployeeRepositoryAdapter that implements EmployeeRepository and throws specific exceptions.
 * Uses EmployeeJpaRepisitory methods inside
 * @see EmployeeRepository
 * @see EmployeeJpaRepository*/
@Repository
@AllArgsConstructor
public class EmployeeRepositoryAdapter implements EmployeeRepository {

    private EmployeeJpaRepository employeeJpaRepository;

    @Override
    public Employee save(Employee employee) {
        return employeeJpaRepository.save(employee);
    }

    @Override
    public Employee findEmployeeByLogin(String login) {
        return employeeJpaRepository.findEmployeeByLogin(login)
                .orElseThrow(() -> new EmployeeNotFoundException(login));
    }

    @Override
    public Employee findEmployeeByUuid(UUID uuid) {
        return employeeJpaRepository.findEmployeeByUuid(uuid)
                .orElseThrow(() -> new EmployeeNotFoundException(uuid));
    }
}
