package ru.aston.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO with employee information")
public class EmployeeInformationDto {

    @Schema(description = "Employee's surname")
    private String surname;

    @Schema(description = "Employee's name")
    private String name;

    @Schema(description = "Employee's middleName")
    private String middleName;

    @Schema(description = "Employee's role in system")
    private String role;

    @Schema(description = "Employee's login in system")
    private String login;

    @Schema(description = "Employee's status in system")
    private String status;

    @Schema(description = "Employee's passport number")
    private String passportId;

    @Schema(description = "Date of issue of the employee's passport")
    private String passportDateIssue;

    @Schema(description = "Creation date of employee's account")
    private String createdAt;

    @Schema(description = "Last modified date of employee's account")
    private String modifiedAt;
}
