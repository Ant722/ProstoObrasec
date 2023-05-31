package ru.aston.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(description = "DTO with password generate info")
@Builder
@Data
public class PasswordGenerateInfoDto {

    @Schema(description = "message")
    private String message;
}
