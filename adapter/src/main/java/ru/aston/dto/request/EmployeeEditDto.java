package ru.aston.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
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
    @NotNull(message = "EmployeeStatus must be not null")
    private EmployeeStatus status;

    /**Role id of edited employee. Can't be null or empty*/
    @NotNull(message = "EmployeeRole must be not null")
    private EmployeeRole role;

    /**Name of edited employee. Can't be null or empty*/
    @NotBlank(message = "Name must be not blank")
    @Length(max = 30, message = "Name is too long, must be 30 characters max")
    private String name;

    /**Surname of edited employee. Can't be null or empty*/
    @NotBlank(message = "Surname must be not blank")
    @Length(max = 30, message = "surname is too long, must be 30 characters max")
    private String surname;

    /**Middlename of edited employee. Not required*/
    @Length(max = 30, message = "middlename is too long, must be 30 characters max")
    private String middleName;

    /**Login of edited employee. Can't be null or empty*/
    @NotBlank(message = "Login must be not blank")
    @Length(max = 30, message = "login is too long, must be 30 characters max")
    private String login;

    /**Passport data of edited employee. Can't be null or empty*/
    @NotBlank(message = "Passport number must be not blank")
    private String passport;

    /**Passport date issue of edited employee. Can't be null or empty*/
    @NotBlank(message = "Passport date issue must be not blank")
    private String passportDateIssue;
}
