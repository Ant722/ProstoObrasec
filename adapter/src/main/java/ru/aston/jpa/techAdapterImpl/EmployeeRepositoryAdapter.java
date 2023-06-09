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
import ru.aston.request.EmployeeSearchCriteria;

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
    public Page<Employee> searchEmployeesByUsername(EmployeeSearchCriteria searchCriteria) {
        QEmployee employee = QEmployee.employee;
        String surname = searchCriteria.getSurname();

        BooleanBuilder predicate = new BooleanBuilder()
                .and(employee.status.eq(EmployeeStatus.valueOf(searchCriteria.getStatus())))
                .and(employee.role.eq(EmployeeRole.valueOf(searchCriteria.getRole())))
                .and(surname != null ? employee.surname.containsIgnoreCase(surname) : null);

        PageRequest pageRequest = PageRequest.of(
                searchCriteria.getPage(), defaultPageSize, Sort.Direction.ASC, searchCriteria.getSort());
        try {
            return employeeJpaRepository.findAll(predicate, pageRequest);
        } catch (PropertyReferenceException e) {
            throw new ValidationException("Sort field is invalid");
        }
    }
}
