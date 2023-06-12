package ru.aston.app.api_impl.entity_factory;

import ru.aston.model.Employee;
import ru.aston.model.enumeration.EmployeeRole;
import ru.aston.model.enumeration.EmployeeStatus;

import java.time.LocalDate;
import java.util.UUID;

public class EmployeeFactory {

    private static final UUID VALID_UUID = UUID.randomUUID();
    private static final String VALID_LOGIN = "TestLogin";
    private static final String VALID_NAME = "TestName";
    private static final String VALID_SURNAME = "TestSurname";
    private static final String VALID_MIDDLE_NAME = "TestMiddleName";
    private static final String VALID_PASSWORD = "3R98FSH^KAejf3p)";
    private static final LocalDate VALID_DATE_ISSUE = LocalDate.of(2025,12,22);
    private static final String VALID_PASSPORT_ID = "TestPassportId";
    private static final EmployeeRole VALID_ROLE = EmployeeRole.CLIENT_MANAGER;
    private static final EmployeeStatus VALID_STATUS = EmployeeStatus.ACTIVE;
    private static final String INVALID_LOGIN = "login";
    public static Employee getValidEmployee(){
        Employee employee = new Employee();
        employee.setUuid(VALID_UUID);
        employee.setLogin(VALID_LOGIN);
        employee.setName(VALID_NAME);
        employee.setSurname(VALID_SURNAME);
        employee.setMiddleName(VALID_MIDDLE_NAME);
        employee.setPassword(VALID_PASSWORD);
        employee.setPassportDateIssue(VALID_DATE_ISSUE);
        employee.setPassportId(VALID_PASSPORT_ID);
        employee.setRole(VALID_ROLE);
        employee.setStatus(VALID_STATUS);
        return employee;
    }

    public static Employee getInvalidEmployee(){
        Employee employee = new Employee();
        employee.setUuid(VALID_UUID);
        employee.setLogin(INVALID_LOGIN);
        employee.setName(VALID_NAME);
        employee.setSurname(VALID_SURNAME);
        employee.setMiddleName(VALID_MIDDLE_NAME);
        employee.setPassword(VALID_PASSWORD);
        employee.setPassportDateIssue(VALID_DATE_ISSUE);
        employee.setPassportId(VALID_PASSPORT_ID);
        employee.setRole(VALID_ROLE);
        employee.setStatus(VALID_STATUS);
        return employee;
    }
}
