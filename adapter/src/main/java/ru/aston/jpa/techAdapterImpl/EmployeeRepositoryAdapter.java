package ru.aston.jpa.techAdapterImpl;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.aston.app.api.repositories.EmployeeRepository;
import ru.aston.exception.EmployeeNotFoundException;
import ru.aston.jpa.repositories.EmployeeJpaRepository;
import ru.aston.model.Employee;
import ru.aston.model.QEmployee;
import ru.aston.model.enumeration.EmployeeRole;
import ru.aston.model.enumeration.EmployeeStatus;

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
    public Page<Employee> searchEmployeesByUsername(String status, String role, String sort, String surname, Integer page) {
        QEmployee employee = QEmployee.employee;

        BooleanBuilder predicate = new BooleanBuilder()
                .and(employee.status.eq(EmployeeStatus.valueOf(status)))
                .and(employee.role.eq(EmployeeRole.valueOf(role)))
                .and(surname != null ? employee.surname.startsWith(surname) : null);


        PageRequest pageRequest = PageRequest.of(page, 30, Sort.Direction.ASC, sort);
        return employeeJpaRepository.findAll(predicate, pageRequest);
    }
}
