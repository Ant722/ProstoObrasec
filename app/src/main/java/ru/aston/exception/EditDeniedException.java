package ru.aston.exception;

import java.io.Serial;

public class EditDeniedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -7065616666972576365L;
    private static final String MESSAGE = "Access denied! Admin rights required";

    public EditDeniedException() {
        super(String.format(MESSAGE));
    }
}
