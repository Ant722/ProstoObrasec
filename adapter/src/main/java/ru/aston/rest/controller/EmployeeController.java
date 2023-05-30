package ru.aston.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.dto.request.EmployeeUpdateDto;
import ru.aston.facade.EmployeeFacade;

import java.util.UUID;

import static ru.aston.util.ValidationConstants.UUID_PATTERN;

@Tag(name = "Employee controller", description = "Controller accepts requests for different actions with employee data in DB")
@RestController
@RequestMapping("api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeFacade employeeFacade;

    @Operation(
            summary = "Employee data updating"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Employee successfully updated",
                    content = @Content),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST",
                    content = @Content),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflict",
                    content = @Content),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content)}
    )

    @PutMapping("/{employee_uuid}")
    public ResponseEntity<Void> updateEmployee(
            @Pattern(regexp = UUID_PATTERN)
            @PathVariable(value = "employee_uuid") UUID uuid,
            @Valid
            @RequestBody EmployeeUpdateDto dto
    ) {
        employeeFacade.updateEmployeeInfo(dto, uuid);
        return ResponseEntity.ok().build();
    }
}