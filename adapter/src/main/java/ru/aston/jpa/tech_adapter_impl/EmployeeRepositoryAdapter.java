package ru.aston.jpa.tech_adapter_impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.aston.app.repositories.EmployeeRepository;
import ru.aston.jpa.repositories.EmployeeJpaRepository;
import ru.aston.model.Employee;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryAdapter implements EmployeeRepository {

    private EmployeeJpaRepository employeeJpaRepository;
    @Override
    public Employee findEmployeeByUuid(UUID uuid) {
        return employeeJpaRepository.findEmployeeByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Employee uuid not found"));

        // todo replace RuntimeException to A-Money project Exception
    }
}
