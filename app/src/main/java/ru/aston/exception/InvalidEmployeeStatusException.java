package ru.aston.exception;

import java.io.Serial;

public class InvalidEmployeeStatusException extends ArrayIndexOutOfBoundsException {

    @Serial
    private static final long serialVersionUID = -1756873681563589307L;
    private static final String INVALID_STATUS_ID_MESSAGE = "Status id %d is invalid";

    public InvalidEmployeeStatusException(int statusId) {
        super(String.format(INVALID_STATUS_ID_MESSAGE, statusId));
    }
}
