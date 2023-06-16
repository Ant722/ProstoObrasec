package ru.aston.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "DTO with password generate info")
public class PasswordGenerateInfoDto {

    @Schema(description = "message")
    private String message;
}
