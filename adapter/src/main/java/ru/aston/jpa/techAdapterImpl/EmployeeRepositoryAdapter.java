package ru.aston.jpa.techAdapterImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.aston.app.api.repositories.EmployeeRepository;
import ru.aston.exception.EmployeeNotFoundException;
import ru.aston.jpa.repositories.EmployeeJpaRepository;
import ru.aston.model.Employee;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryAdapter implements EmployeeRepository {

    private final EmployeeJpaRepository employeeJpaRepository;

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeJpaRepository.findById(id)
                .orElseThrow(() -> {
                    throw new EmployeeNotFoundException(id);
                });
    }
}
