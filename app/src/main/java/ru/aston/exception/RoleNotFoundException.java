package ru.aston.exception;

import java.io.Serial;

public class RoleNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3767858475304429644L;

    public static final String NOT_FOUND_BY_ID_MESSAGE = "No role found with current id = (%d)";

    public RoleNotFoundException(Integer id) {
        super(String.format(NOT_FOUND_BY_ID_MESSAGE, id));
    }
}
