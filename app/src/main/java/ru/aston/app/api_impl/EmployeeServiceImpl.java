package ru.aston.app.api_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.app.api.repositories.EmployeeRepository;
import ru.aston.app.api.repositories.GeneratePasswordRepository;
import ru.aston.app.api.services.EmployeeService;
import ru.aston.app.api.services.MailService;
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
    private final GeneratePasswordRepository generatePasswordRepository;
    private final MailService mailService;

    @Override
    public Employee getEmployeeByUuid(UUID uuid) {
        Employee employee = employeeRepository.findEmployeeByUuid(uuid);
        log.info("Taken from employee with UUID {}", employee.getUuid());
        return employee;
    }

    /**
     * Accepts employee data to create Employee in DB. Checks uuid, login and passport id uniqueness before creating
     */
    @Override
    @Transactional
    public String createNewEmployee(Employee employee) {
        UUID uuid = UUID.randomUUID();

        while (employeeRepository.existByUuid(uuid)){
            uuid = UUID.randomUUID();
        }

        checkExistedLogin(employee.getLogin());
        checkExistedPassportId(employee.getPassportId());

        employee.setUuid(uuid);
        String password = PasswordGeneratorUtils.generatePassword();
        GeneratePassword generatePassword = new GeneratePassword();
        generatePassword.setPassword(password);
        employee.setGeneratePassword(generatePassword);
        generatePasswordRepository.save(generatePassword);
        employeeRepository.save(employee);
        mailService.sendSimpleEmailFromGeneratePassword(employee);
        log.info("New employee with login ({}) was registered",employee.getLogin());
        return uuid.toString();
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

    private void checkExistedLogin(String login){
        if(employeeRepository.existByLogin(login)) {
            log.info("Employee with login = ({}) was not created because this login already " +
                    "belongs to another employee", login);
            throw new LoginConflictException();
        }
    }

    private void checkExistedPassportId(String passportId){
        if(employeeRepository.existByPassportId(passportId)){
            log.info("Employee with passport id = ({}) was not created because this passport" +
                    " id used another employee",passportId);
            throw new PassportIdConflictException(passportId);
        }
    }
}
