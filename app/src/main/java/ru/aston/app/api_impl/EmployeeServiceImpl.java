package ru.aston.app.api_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.app.api.repositories.EmployeeRepository;
import ru.aston.app.api.services.EmployeeService;
import ru.aston.exception.PasswordGenerateTimeException;
import ru.aston.exception.EmployeeNotFoundException;
import ru.aston.exception.LoginConflictException;
import ru.aston.model.Employee;
import ru.aston.model.GeneratePassword;
import ru.util.PasswordGeneratorUtils;
import ru.aston.request.EmployeeSearchCriteria;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 * Service class for Employee. Contains different operations for actions with employees
 */
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
    public Employee generatePasswordByUuid(UUID uuid) {
        Employee employee = employeeRepository.findEmployeeByUuid(uuid);
        GeneratePassword generatePassword = employee.getGeneratePassword();
        checkTimeGeneratePassword(generatePassword.getModifiedAt());
        String oldPassword = generatePassword.getPassword();
        String newPassword = PasswordGeneratorUtils.generatePassword();
        while (oldPassword.equals(newPassword)) {
            newPassword = PasswordGeneratorUtils.generatePassword();
        }
        generatePassword.setPassword(newPassword);
        employee.setGeneratePassword(generatePassword);
        employeeRepository.save(employee);
        log.info("Password from employee UUID {} generate", employee.getUuid());
        return employee;
    }

    private void checkTimeGeneratePassword(LocalDateTime generatePasswordTime) {
        if (LocalDateTime.now().isBefore(generatePasswordTime.plus(10, ChronoUnit.MINUTES))) {
            throw new PasswordGenerateTimeException();
        }
    }

    /**
     * Accepts employee data to update and updates Employee in DB. Checks login uniqueness before updating
     */
    @Override
    public void updateEmployeeInfo(Employee employee, UUID uuid) {
        try {
            if (!findEmployeeByLogin(employee.getLogin()).getUuid().equals(uuid)) {
                log.info("Employee with UUID = ({}) was not updated because login ({}) already " +
                        "belongs to another employee", uuid, employee.getLogin());
                throw new LoginConflictException();
            }
        } catch (EmployeeNotFoundException ignored) {
        }
        Employee employeeToUpdate = employeeRepository.findEmployeeByUuid(uuid);
        employee.setId(employeeToUpdate.getId());
        employee.setUuid(employeeToUpdate.getUuid());
        employee.setGeneratePassword(employeeToUpdate.getGeneratePassword());
        employee.setCreatedAt(employeeToUpdate.getCreatedAt());
        employeeRepository.save(employee);
        log.info("User with UUID ({}) successfully updated", uuid);
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

    private Employee findEmployeeByLogin(String login) {
        return employeeRepository.findEmployeeByLogin(login);
    }
}

