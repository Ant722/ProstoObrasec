package ru.aston.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.aston.model.enumeration.EmployeeRole;

@Schema(description = "DTO to work with Employee Role")
@Data
public class EmployeeRoleDto {

    @Schema(description = "Employee role. Can't be null or empty")
    @NotNull(message = "EmployeeRole must be not null")
    private EmployeeRole role;

    public EmployeeRoleDto(String role) {
        this.role = EmployeeRole.valueOf(role);
    }
}
