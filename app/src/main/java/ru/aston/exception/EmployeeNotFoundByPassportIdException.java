package ru.aston.exception;

import java.io.Serial;

public class EmployeeNotFoundByPassportIdException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 5289303100147560578L;

    public static final String NOT_FOUND_BY_PASSPORT_ID_MESSAGE= "No employee found with current passportId: (%s)";

    public EmployeeNotFoundByPassportIdException(String passportId){
        super(String.format(NOT_FOUND_BY_PASSPORT_ID_MESSAGE, passportId));
    }
}
