package ru.aston.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import ru.aston.model.enumeration.EmployeeRole;
import ru.aston.model.enumeration.EmployeeStatus;
import ru.aston.util.ValidationConstants;

import java.util.UUID;

/**
 * DTO for receiving and checking input data from view. Receive data of edited employee and uuid of admin
 * to allow editing employee data
 */
@Data
public class EmployeeEditDto {

    /**Status id of edited employee. Can't be null or empty*/
    @NotNull
    private EmployeeStatus status;

    /**Role id of edited employee. Can't be null or empty*/
    @NotNull
    private EmployeeRole role;

    /**Name of edited employee. Can't be null or empty*/
    @NotBlank
    private String name;

    /**Surname of edited employee. Can't be null or empty*/
    @NotBlank
    private String surname;

    /**Middlename of edited employee. Not required*/
    private String middleName;

    /**Login of edited employee. Can't be null or empty*/
    @NotBlank
    private String login;

    /**Passport data of edited employee. Can't be null or empty*/
    @NotBlank
    private String passport;

    /**Passport date issue of edited employee. Can't be null or empty*/
    @NotBlank
    private String passportDateIssue;
}
