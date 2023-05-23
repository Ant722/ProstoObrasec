package ru.aston.appimpl.services_impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.app.repositories.EmployeeRepository;
import ru.aston.app.services.EmployeeService;
import ru.aston.exception.EditDeniedException;
import ru.aston.model.Employee;
import ru.aston.model.enumeration.EmployeeRole;

import java.util.UUID;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;

    @Override
    public boolean validAdminByUuid(UUID uuidForCheckAdmin) {
        Employee employee = employeeRepository.findEmployeeByUuid(uuidForCheckAdmin);
        if (employee.getRole().getName().equals(EmployeeRole.ADMIN)) {
            return true;
        } else {
            throw new EditDeniedException();
        }
    }

    @Override
    public boolean updateEmployeeInfo(Employee employee, UUID uuidForCheckAdmin, Long employeeId) {
        validAdminByUuid(uuidForCheckAdmin);
        Employee employeeToUpdate = employeeRepository.findEmployeeById(employeeId);
        employeeToUpdate.setStatus(employee.getStatus());
        employeeToUpdate.setRole(employee.getRole());
        employeeToUpdate.setName(employee.getName());
        employeeToUpdate.setMiddleName(employee.getMiddleName());
        employeeToUpdate.setSurname(employee.getSurname());
        employeeToUpdate.setLogin(employee.getLogin());
        employeeToUpdate.setPassportId(employee.getPassportId());
        employeeToUpdate.setPassportDateIssue(employee.getPassportDateIssue());
        employeeRepository.save(employeeToUpdate);
        return false;
    }
}
