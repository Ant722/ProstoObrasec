package ru.aston.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Employee registration DTO")
public class EmployeeCreateDto {

    @NotBlank
    @Schema(description = "Surname of employee. Cannot be null or empty")
    private String surname;

    @NotBlank
    @Schema(description = "Name of employee. Cannot be null or empty")
    private String name;

    @Schema(description = "Middle name of employee. Not required")
    private String middleName;

    @NotNull
    @Schema(description = "Role of employee. Cannot be null or empty")
    private EmployeeRoleDto role;

    @NotBlank
    @Schema(description = "Login of employee. Cannot be null or empty")
    private String login;

    @NotNull
    @Schema(description = "Status of employee. Cannot be null or empty")
    private EmployeeStatusDto status;

    @NotBlank
    @Schema(description = "Passport data. Cannot be null or empty")
    private String passportId;

    @NotBlank
    @Schema(description = "Date issue of passport. Can not be null or empty")
    private String passportDateIssue;

}
