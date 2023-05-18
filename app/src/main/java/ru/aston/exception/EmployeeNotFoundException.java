package ru.aston.exception;

public class EmployeeNotFoundException extends RuntimeException{

    public static final String EMPLOYEE_NOT_FOUND_BY_ID = "Account for employee with id %d not found";

    public EmployeeNotFoundException(Long id) {
        super(String.format(EMPLOYEE_NOT_FOUND_BY_ID, id));
    }
}
