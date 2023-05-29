package ru.aston.appimpl.services_impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.aston.app.repositories.EmployeeRepository;
import ru.aston.app.services.EmployeeService;
import ru.aston.exception.EmployeeNotFoundException;
import ru.aston.exception.LoginConflictException;
import ru.aston.model.Employee;

import java.util.UUID;

/**Service class for Employee. Contains different operations for actions with employees*/
@Slf4j
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;


    @Override
    public Employee findEmployeeByLogin(String login) {
        return employeeRepository.findEmployeeByLogin(login);
    }

    /**Accepts employee data to edit and edits Employee in DB. Checks login uniqueness before editing*/
    @Override
    public void updateEmployeeInfo(Employee employee, UUID uuid) {
        try {
            if (!findEmployeeByLogin(employee.getLogin()).getUuid().equals(uuid)) {
                log.info("Employee with UUID = ({}) was not updated because login ({}) already " +
                        "belongs to another employee", uuid, employee.getLogin());
                throw new LoginConflictException();
            }
        } catch (EmployeeNotFoundException ignored) {}
        Employee employeeToUpdate = employeeRepository.findEmployeeByUuid(uuid);
        employeeToUpdate.setStatus(employee.getStatus());
        employeeToUpdate.setRole(employee.getRole());
        employeeToUpdate.setName(employee.getName());
        employeeToUpdate.setMiddleName(employee.getMiddleName());
        employeeToUpdate.setSurname(employee.getSurname());
        employeeToUpdate.setLogin(employee.getLogin());
        employeeToUpdate.setPassportId(employee.getPassportId());
        employeeToUpdate.setPassportDateIssue(employee.getPassportDateIssue());
        employeeRepository.save(employeeToUpdate);
        log.info("User with UUID ({}) successfully updated", uuid);
    }
}
