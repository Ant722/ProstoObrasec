package ru.aston.exception;

import java.io.Serial;

public class InvalidEmployeeRoleException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 6128826768292359144L;
    private static final String INVALID_ROLE_ID_MESSAGE = "Role id %d is invalid";

    public InvalidEmployeeRoleException(int roleId) {
        super(String.format(INVALID_ROLE_ID_MESSAGE, roleId));
    }
}
