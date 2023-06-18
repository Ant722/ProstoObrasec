package ru.aston.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(description = "DTO with employee data to update employee in DB")
public class EmployeeUpdateDto {

    @Schema(description = "New employee status. Can't be null or empty")
    @NotNull(message = "EmployeeStatus must be not null")
    private EmployeeStatusDto status;

    @Schema(description = "New employee role. Can't be null or empty")
    @NotNull(message = "EmployeeRole must be not null")
    private EmployeeRoleDto role;

    @Schema(description = "New employee name. Can't be null or empty")
    @NotBlank(message = "Name must be not blank")
    @Length(max = 30, message = "Name is too long, must be 30 characters max")
    private String name;

    @Schema(description = "New employee surname. Can't be null or empty")
    @NotBlank(message = "Surname must be not blank")
    @Length(max = 30, message = "Surname is too long, must be 30 characters max")
    private String surname;

    @Schema(description = "New employee middlename. Not required")
    @Length(max = 30, message = "Middlename is too long, must be 30 characters max")
    private String middleName;

    @Schema(description = "New employee login. Can't be null or empty. Must be unique")
    @NotBlank(message = "Login must be not blank")
    @Length(max = 30, message = "Login is too long, must be 30 characters max")
    private String login;

    @Schema(description = "New employee passport data. Can't be null or empty")
    @NotBlank(message = "Passport number must be not blank")
    @Length(max = 11, message = "Passport number is too long, must be 11 characters max")
    private String passport;

    @Schema(description = "New employee passport date issue. Can't be null or empty")
    @NotBlank(message = "Passport date issue must be not blank")
    private String passportDateIssue;
}
