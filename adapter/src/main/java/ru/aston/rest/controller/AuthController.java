package ru.aston.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.dto.request.LoginRequestDto;
import ru.aston.dto.response.EmployeeAuthInfoResponseDto;
import ru.aston.facade.EmployeeFacade;

@Tag(name = "Authorisation Controller", description = "Controller to authorise Employee")
@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final EmployeeFacade employeeFacade;

    @Operation(
            summary = "Admin authorisation",
            description = "Allows to get uuid employee role"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST",
                    content = @Content),
            @ApiResponse(
                    responseCode = "403",
                    description = "FORBIDDEN",
                    content = @Content),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content)}
    )
    @PostMapping
    public ResponseEntity<EmployeeAuthInfoResponseDto> login(
            @Parameter(description = "DTO for get employee's uuid, password and role")
            @Valid
            @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(employeeFacade.getInformationByLogin(loginRequestDto));
    }
}
