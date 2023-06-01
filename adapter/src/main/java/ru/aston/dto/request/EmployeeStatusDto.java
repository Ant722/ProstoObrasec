package ru.aston.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.aston.model.enumeration.EmployeeStatus;

@Schema(description = "DTO to work with Employee Role")
@Data
public class EmployeeStatusDto {

    @Schema(description = "Employee status. Can't be null or empty")
    @NotNull(message = "EmployeeStatus must be not null")
    private EmployeeStatus status;

    public EmployeeStatusDto(String status) {
        this.status = EmployeeStatus.valueOf(status);
    }
}
