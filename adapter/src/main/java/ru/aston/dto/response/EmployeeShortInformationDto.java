package ru.aston.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO with employee short information for list")
public class EmployeeShortInformationDto {

    @Schema(description = "Employee's UUID")
    private String uuid;

    @Schema(description = "Employee's surname")
    private String surname;

    @Schema(description = "Employee's name")
    private String name;

    @Schema(description = "Employee's middleName")
    private String middleName;

    @Schema(description = "Employee's role in system")
    private String role;

    @Schema(description = "Employee's status in system")
    private String status;

    @Schema(description = "Creation date of employee's account")
    private String createdAt;

    @Schema(description = "Last modified date of employee's account")
    private String modifiedAt;
}
