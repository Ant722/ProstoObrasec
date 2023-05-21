package ru.aston.appimpl.services_impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.app.repositories.EmployeeRepository;
import ru.aston.app.services.EmployeeService;
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
            throw new RuntimeException("You have no admin rights");

            // todo replace to A-Money Exception
        }
    }

    @Override
    public boolean updateEmployeeInfo(Employee employee, UUID uuidForCheckAdmin) {
        validAdminByUuid(uuidForCheckAdmin);
        System.out.println("From EmployeeServiceImpl1" + employee.getLogin());
        Employee employeeToUpdate = employeeRepository.findEmployeeByLogin(employee.getLogin());
        System.out.println("From EmployeeServiceImpl2" + employeeToUpdate.getLogin());
        return false;
    }
}
