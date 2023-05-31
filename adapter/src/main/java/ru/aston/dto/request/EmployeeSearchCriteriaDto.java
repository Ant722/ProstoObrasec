package ru.aston.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.aston.model.enumeration.EmployeeRole;
import ru.aston.model.enumeration.EmployeeStatus;

@Data
@Schema(description = "DTO with search criteria")
public class EmployeeSearchCriteriaDto {

    @NotNull
    @Schema(description = "Search by status")
    private EmployeeStatus status;

    @NotNull
    @Schema(description = "Search by role")
    private EmployeeRole role;

    @NotBlank
    @Schema(description = "Sorting parameter")
    String sort;

    @Schema(description = "String for partial surname match")
    String surname;

    @Min(1)
    @Schema(description = "Current page")
    Integer page;
}
