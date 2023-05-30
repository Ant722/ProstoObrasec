package ru.aston.jpa.techAdapterImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.aston.app.api.repositories.EmployeeRepository;
import ru.aston.exception.EmployeeNotFoundException;
import ru.aston.jpa.repositories.EmployeeJpaRepository;
import ru.aston.model.Employee;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryAdapter implements EmployeeRepository {

    private final EmployeeJpaRepository employeeJpaRepository;

    @Override
    public Employee findEmployeeByUuid(UUID uuid) {
        return employeeJpaRepository.findByUuid(uuid)
                .orElseThrow(() -> {
                    throw new EmployeeNotFoundException(uuid);
                });
    }

    @Override
    public void save(Employee employee) {
        employeeJpaRepository.save(employee);
    }
}
