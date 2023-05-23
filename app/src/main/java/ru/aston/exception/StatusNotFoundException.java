package ru.aston.exception;

import java.io.Serial;

public class StatusNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2145257976433737485L;

    public static final String NOT_FOUND_BY_ID_MESSAGE = "No status found with current id = (%d)";

    public StatusNotFoundException(Integer id) {
        super(String.format(NOT_FOUND_BY_ID_MESSAGE, id));
    }
}
