package ru.aston.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import static ru.aston.util.ValidationConstants.PASSWORD_PATTERN;

@Data
@Schema(description = "DTO for employee authentication")
public class LoginRequestDto {

    @Schema(description = "Employee's login")
    @NotBlank(message = "login should not be blank")
    private String login;

    @Schema(description = "Employee's password")
    @NotBlank
    @Pattern(regexp = PASSWORD_PATTERN,
            message = "Invalid Format, password should be at least 6 symbols and contain at least " +
                    "1 uppercase letter, 1 lowercase 1 digit, 1 special character")
    private String password;
}