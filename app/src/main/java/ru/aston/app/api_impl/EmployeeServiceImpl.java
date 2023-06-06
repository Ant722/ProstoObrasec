package ru.aston.app.api_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.aston.app.api.repositories.EmployeeRepository;
import ru.aston.app.api.services.EmployeeService;
import ru.aston.exception.PasswordGenerateTimeException;
import ru.aston.model.Employee;
import ru.aston.model.GeneratePassword;
import ru.util.PasswordGeneratorUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
}
