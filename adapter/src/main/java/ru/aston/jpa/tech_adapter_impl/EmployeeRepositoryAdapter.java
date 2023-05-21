package ru.aston.jpa.tech_adapter_impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.aston.app.repositories.EmployeeRepository;
import ru.aston.jpa.repositories.EmployeeJpaRepository;
import ru.aston.model.Employee;

import java.util.UUID;

@Repository
@AllArgsConstructor
public class EmployeeRepositoryAdapter implements EmployeeRepository {

    private EmployeeJpaRepository employeeJpaRepository;

    @Override
    public Employee findEmployeeByLogin(String id) {
        return employeeJpaRepository.findEmployeeByLogin(id)
                .orElseThrow(() -> new RuntimeException("Employee login not found"));

        // todo replace RuntimeException to A-Money project Exception
    }

    @Override
    public Employee findEmployeeByUuid(UUID uuid) {
        return employeeJpaRepository.findEmployeeByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Employee uuid not found"));

        // todo replace RuntimeException to A-Money project Exception
    }
}
