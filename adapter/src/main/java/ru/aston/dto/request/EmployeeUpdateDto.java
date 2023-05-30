package ru.aston.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.aston.model.enumeration.EmployeeRole;
import ru.aston.model.enumeration.EmployeeStatus;

@Data
@Schema(description = "DTO with employee data to update employee in DB")
public class EmployeeUpdateDto {

    @Schema(description = "New employee status. Can't be null or empty")
    @NotNull(message = "EmployeeStatus must be not null")
    private EmployeeStatus status;

    @Schema(description = "New employee role. Can't be null or empty")
    @NotNull(message = "EmployeeRole must be not null")
    private EmployeeRole role;

    @Schema(description = "New employee name. Can't be null or empty")
    @NotBlank(message = "Name must be not blank")
    @Length(max = 30, message = "Name is too long, must be 30 characters max")
    private String name;

    @Schema(description = "New employee surname. Can't be null or empty")
    @NotBlank(message = "Surname must be not blank")
    @Length(max = 30, message = "surname is too long, must be 30 characters max")
    private String surname;

    @Schema(description = "New employee middlename. Not required")
    @Length(max = 30, message = "middlename is too long, must be 30 characters max")
    private String middleName;

    @Schema(description = "New employee login. Can't be null or empty. Must be unique")
    @NotBlank(message = "Login must be not blank")
    @Length(max = 30, message = "login is too long, must be 30 characters max")
    private String login;

    @Schema(description = "New employee passport data. Can't be null or empty")
    @NotBlank(message = "Passport number must be not blank")
    private String passport;

    @Schema(description = "New employee passport date issue. Can't be null or empty")
    @NotBlank(message = "Passport date issue must be not blank")
    private String passportDateIssue;
}
