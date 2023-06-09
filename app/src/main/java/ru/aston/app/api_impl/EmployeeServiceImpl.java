package ru.aston.app.api_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.app.api.repositories.EmployeeRepository;
import ru.aston.app.api.services.EmployeeService;
import ru.aston.model.Employee;
import ru.aston.request.EmployeeSearchCriteria;

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
    @Transactional(readOnly = true, timeout = 5)
    public Page<Employee> searchEmployeesByUsername(EmployeeSearchCriteria searchCriteria) {
        Page<Employee> employeePage = employeeRepository.searchEmployeesByUsername(searchCriteria);
        log.info(
                "Taken from employee {} records, current page is {}",
                employeePage.getTotalElements(),
                searchCriteria.getPage());
        return employeePage;
    }
}
