package ru.aston.exception;

import java.util.UUID;

public class EmployeeNotFoundException extends RuntimeException{

    public static final String EMPLOYEE_NOT_FOUND_BY_UUID = "Account for employee with UUID %s not found";

    public EmployeeNotFoundException(UUID uuid) {
        super(String.format(EMPLOYEE_NOT_FOUND_BY_UUID, uuid));
    }
}
