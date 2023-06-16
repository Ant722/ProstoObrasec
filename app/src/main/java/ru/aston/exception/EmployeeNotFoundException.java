package ru.aston.exception;

import java.io.Serial;
import java.util.UUID;

public class EmployeeNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 7250603195899742560L;
    public static final String EMPLOYEE_NOT_FOUND_BY_UUID = "Account for employee with UUID %s not found";
    private static final String NOT_FOUND_BY_LOGIN_MESSAGE = "No employee found with current login: (%s)";

    public EmployeeNotFoundException(UUID uuid) {
        super(String.format(EMPLOYEE_NOT_FOUND_BY_UUID, uuid));
    }

    public EmployeeNotFoundException(String login) {
        super(String.format(NOT_FOUND_BY_LOGIN_MESSAGE, login));
    }
}
