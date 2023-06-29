package ru.aston.entity.factory;

import lombok.Data;

@Data
public class EmployeeRegisteredRequestBodyFactory {


    private String surname;

    private String name;

    private String middleName;

    private String role;

    private String login;

    private String status;

    private String passportId;

    private String passportDateIssue;

    private EmployeeRegisteredRequestBodyFactory(){
    }

    public static EmployeeRegisteredRequestBodyFactory getValidRegistrationEmployeeRequestDto(){
        EmployeeRegisteredRequestBodyFactory newRegisteredEmployee = new EmployeeRegisteredRequestBodyFactory();

        newRegisteredEmployee.setName("name");
        newRegisteredEmployee.setSurname("SURNAME");
        newRegisteredEmployee.setMiddleName("midAME");
        newRegisteredEmployee.setRole("ADMIN");
        newRegisteredEmployee.setStatus("ACTIVE");
        newRegisteredEmployee.setPassportDateIssue("UE");
        newRegisteredEmployee.setPassportId("PASSID");
        newRegisteredEmployee.setLogin("LOGIN");
        return newRegisteredEmployee;
    }

    public static EmployeeRegisteredRequestBodyFactory getRegistrationEmployeeRequestDtoInvalidLogin(){
        EmployeeRegisteredRequestBodyFactory newRegisteredEmployee = new EmployeeRegisteredRequestBodyFactory();
        newRegisteredEmployee.setName(null);
        newRegisteredEmployee.setSurname("SURNAME");
        newRegisteredEmployee.setMiddleName("midAME");
        newRegisteredEmployee.setRole("ADMIN");
        newRegisteredEmployee.setStatus("ACTIVE");
        newRegisteredEmployee.setPassportDateIssue("UE");
        newRegisteredEmployee.setPassportId("PASSID");
        newRegisteredEmployee.setLogin("LOGIN");
        return newRegisteredEmployee;
    }

    public static EmployeeRegisteredRequestBodyFactory getRegistrationEmployeeRequestDtoExistedLogin(){
        EmployeeRegisteredRequestBodyFactory newRegisteredEmployee = new EmployeeRegisteredRequestBodyFactory();
        newRegisteredEmployee.setName("exist");
        newRegisteredEmployee.setSurname("SURNAME");
        newRegisteredEmployee.setMiddleName("midAME");
        newRegisteredEmployee.setRole("ADMIN");
        newRegisteredEmployee.setStatus("ACTIVE");
        newRegisteredEmployee.setPassportDateIssue("UE");
        newRegisteredEmployee.setPassportId("PASSID");
        newRegisteredEmployee.setLogin("LOGIN");
        return newRegisteredEmployee;
    }



}
