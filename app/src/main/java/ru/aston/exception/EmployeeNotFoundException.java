package ru.aston.exception;

import java.io.Serial;
import java.util.UUID;

public class EmployeeNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 7250603195899742560L;
    private static final String NOT_FOUND_BY_UUID_MESSAGE = "No employee found with current uuid: (%s)";
    private static final String NOT_FOUND_BY_LOGIN_MESSAGE = "No employee found with current login: (%s)";
    public static final String NOT_FOUND_BY_ID_MESSAGE = "No employee found with current id = (%d)";

    public EmployeeNotFoundException(Long id) {
        super(String.format(NOT_FOUND_BY_ID_MESSAGE, id));
    }

    public EmployeeNotFoundException(UUID uuid) {
        super(String.format(NOT_FOUND_BY_UUID_MESSAGE, uuid));
    }

    public EmployeeNotFoundException(String login) {
        super(String.format(NOT_FOUND_BY_LOGIN_MESSAGE, login));
    }
}
