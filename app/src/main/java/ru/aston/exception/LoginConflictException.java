package ru.aston.exception;

import java.io.Serial;

public class LoginConflictException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1060813597866320013L;

    private static final String LOGIN_CONFLICT_MESSAGE = "Пользователь с таким логином уже существует";

    public LoginConflictException() {
        super(String.format(LOGIN_CONFLICT_MESSAGE));
    }
}
