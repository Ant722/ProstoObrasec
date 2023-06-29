package ru.aston.exception;

import java.io.Serial;

public class WrongPasswordException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2839321515128860181L;
    public static final String WRONG_PASSWORD_MESSAGE = "Password is incorrect";

    public WrongPasswordException() {
        super(String.format(WRONG_PASSWORD_MESSAGE));
    }
}
