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
 * @see EmployeeRepository */
@Repository
@AllArgsConstructor
public class EmployeeRepositoryAdapter implements EmployeeRepository {

    private EmployeeJpaRepository employeeJpaRepository;

    @Override
    public Employee save(Employee employee) {
        return employeeJpaRepository.save(employee);
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeJpaRepository.findEmployeeById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public Employee findEmployeeByUuid(UUID uuid) {
        return employeeJpaRepository.findEmployeeByUuid(uuid)
                .orElseThrow(() -> new EmployeeNotFoundException(uuid));
    }
}
