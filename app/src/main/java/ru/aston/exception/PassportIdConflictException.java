package ru.aston.exception;

import java.io.Serial;

public class PassportIdConflictException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -2259837187037256764L;

    private static final String PASSPORT_ID_CONFLICT_MESSAGE = "Passport with current passport id already exist";

    public PassportIdConflictException(){
        super(String.format(PASSPORT_ID_CONFLICT_MESSAGE));
    }
}
