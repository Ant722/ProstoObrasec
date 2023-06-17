package ru.aston.api_impl.entity_factory;

import ru.aston.model.Employee;
import ru.aston.model.enumeration.EmployeeRole;
import ru.aston.model.enumeration.EmployeeStatus;

import java.time.LocalDate;

public class EmployeeFactory {

    public static Employee getCorrectEmployee() {
        Employee employee = new Employee();
        employee.setSurname("Ivanov");
        employee.setName("Ivan");
        employee.setMiddleName("Ivanovich");
        employee.setLogin("i.ivanov");
        employee.setPassportId("1234 111222");
        employee.setPassportDateIssue(LocalDate.parse("2020-10-10"));
        employee.setRole(EmployeeRole.ADMIN);
        employee.setStatus(EmployeeStatus.ON_VACATION);
        return employee;
    }

    public static Employee getExistedEmployeeForLoginConflict() {
        Employee employee = new Employee();
        employee.setSurname("Petrov");
        employee.setName("Petr");
        employee.setMiddleName("Petrovich");
        employee.setLogin("p.petrov");
        employee.setPassportId("4321 111333");
        employee.setPassportDateIssue(LocalDate.parse("2020-10-10"));
        employee.setRole(EmployeeRole.PRODUCT_MANAGER);
        employee.setStatus(EmployeeStatus.ACTIVE);
        return employee;
    }

    public static Employee getEmployeeWithLoginConflict() {
        Employee employee = new Employee();
        employee.setSurname("Ivanov");
        employee.setName("Ivan");
        employee.setMiddleName("Ivanovich");
        employee.setLogin("p.petrov");
        employee.setPassportId("1234 111222");
        employee.setPassportDateIssue(LocalDate.parse("2020-10-10"));
        employee.setRole(EmployeeRole.ADMIN);
        employee.setStatus(EmployeeStatus.ON_VACATION);
        return employee;
    }
}
