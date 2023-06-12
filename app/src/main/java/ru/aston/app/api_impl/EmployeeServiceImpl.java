package ru.aston.app.api_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.app.api.repositories.EmployeeRepository;
import ru.aston.app.api.services.EmployeeService;
import ru.aston.exception.EmployeeNotFoundByPassportIdException;
import ru.aston.exception.EmployeeNotFoundException;
import ru.aston.exception.LoginConflictException;
import ru.aston.exception.PassportIdConflictException;
import ru.aston.exception.PasswordGenerateTimeException;
import ru.aston.model.Employee;
import ru.aston.model.GeneratePassword;
import ru.util.PasswordGeneratorUtils;

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
    @Transactional
    public void createNewEmployee(Employee employee) {
        UUID uuid = UUID.randomUUID();
        try {
            if(employeeRepository.findEmployeeByLogin(employee.getLogin())!=null){
                log.info("Employee with login = ({}) was not created because this login already " +
                        "belongs to another employee", employee.getLogin());

                throw new LoginConflictException();
            }
        }catch (EmployeeNotFoundException ignored){
        }

        try {
            if(employeeRepository.findEmployeeByPassportId(employee.getPassportId())!=null){
                log.info("Employee with passport id = ({}) was not created because this passport" +
                        " id used another employee",employee.getPassportId());
                throw new PassportIdConflictException();
            }
        } catch (EmployeeNotFoundByPassportIdException ignored) {
        }

        employee.setUuid(uuid);
        employee.setPassword("defaultPass");
        employeeRepository.save(employee);
        log.info("New employee with login ({}) was registered",employee.getLogin());
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
        employee.setPassword(employeeToUpdate.getPassword());
        employee.setCreatedAt(employeeToUpdate.getCreatedAt());
        employeeRepository.save(employee);
        log.info("User with UUID ({}) successfully updated", uuid);
    }

    private Employee findEmployeeByLogin(String login) {
        return employeeRepository.findEmployeeByLogin(login);
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
