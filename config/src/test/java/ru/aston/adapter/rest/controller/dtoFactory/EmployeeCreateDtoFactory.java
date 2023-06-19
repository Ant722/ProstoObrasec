package ru.aston.adapter.rest.controller.dtoFactory;

import lombok.experimental.UtilityClass;
import ru.aston.dto.request.EmployeeCreateDto;
import ru.aston.dto.request.EmployeeRoleDto;
import ru.aston.dto.request.EmployeeStatusDto;

@UtilityClass
public class EmployeeCreateDtoFactory {

    private static final String VALID_LOGIN = "testLogin";
    private static final String INVALID_LOGIN = "login";
    private static final String VALID_NAME = "Name";
    private static final String VALID_SURNAME = "Surname";
    private static final String VALID_MIDDLE_NAME = "Middlename";
    private static final EmployeeRoleDto VALID_ROLE = new EmployeeRoleDto("ADMIN");
    private static final EmployeeStatusDto VALID_STATUS = new EmployeeStatusDto("ACTIVE");
    private static final String VALID_PASSPORT_ID = "129349RI";
    private static final String VALID_PASSPORT_DATE_ISSUE = "2023-01-01";
    public static EmployeeCreateDto getValidRegistrationEmployeeRequestDto(){
        EmployeeCreateDto newRegisteredEmployee = new EmployeeCreateDto();
        newRegisteredEmployee.setName(VALID_NAME);
        newRegisteredEmployee.setSurname(VALID_SURNAME);
        newRegisteredEmployee.setMiddleName(VALID_MIDDLE_NAME);
        newRegisteredEmployee.setRole(VALID_ROLE);
        newRegisteredEmployee.setStatus(VALID_STATUS);
        newRegisteredEmployee.setPassportDateIssue(VALID_PASSPORT_DATE_ISSUE);
        newRegisteredEmployee.setPassportId(VALID_PASSPORT_ID);
        newRegisteredEmployee.setLogin(VALID_LOGIN);
        return newRegisteredEmployee;
    }

    public static EmployeeCreateDto getRegistrationEmployeeRequestDtoInvalidLogin(){
        EmployeeCreateDto newRegisteredEmployee = new EmployeeCreateDto();
        newRegisteredEmployee.setName(VALID_NAME);
        newRegisteredEmployee.setSurname(VALID_SURNAME);
        newRegisteredEmployee.setMiddleName(VALID_MIDDLE_NAME);
        newRegisteredEmployee.setRole(VALID_ROLE);
        newRegisteredEmployee.setStatus(VALID_STATUS);
        newRegisteredEmployee.setPassportDateIssue(VALID_PASSPORT_DATE_ISSUE);
        newRegisteredEmployee.setPassportId(VALID_PASSPORT_ID);
        newRegisteredEmployee.setLogin(INVALID_LOGIN);
        return newRegisteredEmployee;
    }

    public static EmployeeCreateDto getInvalidCreateEmployeeDto(){
        EmployeeCreateDto newRegisteredEmployee = new EmployeeCreateDto();
        newRegisteredEmployee.setName(null);
        newRegisteredEmployee.setSurname(VALID_SURNAME);
        newRegisteredEmployee.setMiddleName(VALID_MIDDLE_NAME);
        newRegisteredEmployee.setRole(VALID_ROLE);
        newRegisteredEmployee.setStatus(VALID_STATUS);
        newRegisteredEmployee.setPassportDateIssue(VALID_PASSPORT_DATE_ISSUE);
        newRegisteredEmployee.setPassportId(VALID_PASSPORT_ID);
        newRegisteredEmployee.setLogin(INVALID_LOGIN);
        return newRegisteredEmployee;
    }
}
