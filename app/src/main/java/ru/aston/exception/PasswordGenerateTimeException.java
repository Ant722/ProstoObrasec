package ru.aston.exception;

public class PasswordGenerateTimeException extends RuntimeException {

    public static final String MESSAGE = "A new password has already been generated and sent to the employee";

    public PasswordGenerateTimeException() {
        super(MESSAGE);
    }
}
