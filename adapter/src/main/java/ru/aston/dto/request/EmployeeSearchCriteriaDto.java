package ru.aston.dto.request;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.aston.model.enumeration.EmployeeRole;
import ru.aston.model.enumeration.EmployeeStatus;

@Data
public class EmployeeSearchCriteriaDto {

    @NotNull
    @Parameter(description = "Search by status")
    private EmployeeStatus status;

    @NotNull
    @Parameter(description = "Search by role")
    private EmployeeRole role;

    @NotBlank
    @Parameter(description = "Sorting parameter")
    String sort;

    @Parameter(description = "String for partial surname match")
    String surname;

    @Min(1)
    @NotNull
    @Parameter(description = "Current page")
    Integer page;
}
