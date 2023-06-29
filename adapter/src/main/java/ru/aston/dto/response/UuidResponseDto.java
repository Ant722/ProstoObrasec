package ru.aston.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "DTO with Uuid of employee")
public class UuidResponseDto {

    @Schema(description = "Uuid of employee")
    private String employeeUuid;
}
