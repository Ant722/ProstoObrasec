package ru.aston.dto.request;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeSearchCriteriaDto {

    @NotNull
    @Parameter(description = "Search by status")
    private EmployeeStatusDto status;

    @NotNull
    @Parameter(description = "Search by role")
    private EmployeeRoleDto role;

    @NotBlank
    @Parameter(description = "Sorting parameter")
    private String sort;

    @Parameter(description = "String for partial surname match")
    private String surname;

    @Min(1)
    @NotNull
    @Parameter(description = "Current page")
    private Integer page;
}
