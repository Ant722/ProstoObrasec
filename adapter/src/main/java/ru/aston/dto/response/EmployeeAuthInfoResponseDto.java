package ru.aston.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "DTO for employee authentication")
public class EmployeeAuthInfoResponseDto {

    @Schema(description = "Employee's uuid")
    @NotBlank
    private String uuid;

    @Schema(description = "Employee's password")
    @NotBlank
    private String password;

    @Schema(description = "Employee's role")
    @NotBlank
    private String role;
}
