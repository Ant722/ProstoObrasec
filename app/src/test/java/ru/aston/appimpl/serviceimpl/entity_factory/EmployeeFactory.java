package ru.aston.appimpl.serviceimpl.entity_factory;

import ru.aston.model.Employee;
import ru.aston.model.GeneratePassword;
import ru.aston.model.enumeration.EmployeeRole;
import ru.aston.model.enumeration.EmployeeStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class EmployeeFactory {

    private static final UUID UUID_EMPLOYEE = UUID.fromString("fe0d0744-fe15-11ed-be56-0242ac120002");

    private static final String SURNAME = "Petrov";

    private static final String NAME = "Ivan";

    private static final String MIDDLE_NAME = "Ivanov";

    private static final String VALID_LOGIN = "i.ivanov";

    private static final String VALID_PASSWORD = "'nS0[uCdtOI,l?";

    private static final String PASSPORT_ID = "21 22 213987";

    private static final EmployeeRole ADMIN_ROLE = EmployeeRole.ADMIN;

    private static final EmployeeStatus ACTIVE_STATUS = EmployeeStatus.ACTIVE;


    public static Employee getValidEmployee() {
        return Employee.builder().id(1L)
                .uuid(UUID_EMPLOYEE)
                .surname(SURNAME)
                .name(NAME)
                .middleName(MIDDLE_NAME)
                .login(VALID_LOGIN)
                .generatePassword(GeneratePassword.builder()
                        .password(VALID_PASSWORD)
                        .modifiedAt(LocalDateTime.MIN)
                        .build())
                .passportId(PASSPORT_ID)
                .passportDateIssue(LocalDate.now())
                .role(ADMIN_ROLE)
                .status(ACTIVE_STATUS)
                .build();
    }

    public static Employee getEmployeeWrongPasswordGenerationDate() {
        return Employee.builder().id(1L)
                .uuid(UUID_EMPLOYEE)
                .generatePassword(GeneratePassword.builder()
                        .password(VALID_PASSWORD)
                        .modifiedAt(LocalDateTime.now())
                        .build())
                .build();
    }
}
