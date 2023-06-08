package ru.aston.jpa.techAdapterImpl;

import com.querydsl.core.BooleanBuilder;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
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

    @Value("${page.default-size}")
    private int defaultPageSize;

    @Override
    public Employee findEmployeeByUuid(UUID uuid) {
        return employeeJpaRepository.findByUuid(uuid)
                .orElseThrow(() -> {
                    throw new EmployeeNotFoundException(uuid);
                });
    }

    @Override
    public Page<Employee> searchEmployeesByUsername(
            String status,
            String role,
            String sort,
            String surname,
            Integer page) {
        QEmployee employee = QEmployee.employee;

        BooleanBuilder predicate = new BooleanBuilder()
                .and(employee.status.eq(EmployeeStatus.valueOf(status)))
                .and(employee.role.eq(EmployeeRole.valueOf(role)))
                .and(surname != null ? employee.surname.contains(surname) : null);

        PageRequest pageRequest = PageRequest.of(page, defaultPageSize, Sort.Direction.ASC, sort);
        try {
            return employeeJpaRepository.findAll(predicate, pageRequest);
        } catch (PropertyReferenceException e) {
            throw new ValidationException("Sort field is invalid");
        }
    }
}
