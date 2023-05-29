package ru.aston.appimpl.services_impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.app.repositories.EmployeeRepository;
import ru.aston.app.services.EmployeeService;
import ru.aston.model.Employee;

import java.util.UUID;

/**Service class for Employee. Contains different operations for actions with employees*/
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;

    /**Accepts employee data to edit and edits Employee in DB*/
    @Override
    public void updateEmployeeInfo(Employee employee, UUID uuid) {
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
    }
}
