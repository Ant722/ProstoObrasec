package ru.aston.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.aston.dto.request.EmployeeCreateDto;
import org.springframework.web.bind.annotation.*;
import ru.aston.dto.request.EmployeeUpdateDto;
import ru.aston.dto.response.EmployeeInformationDto;
import ru.aston.facade.EmployeeFacade;

import static ru.aston.util.ValidationConstants.UUID_PATTERN;

@RequestMapping("api/v1/employee")
@Tag(name = "Employee Controller", description = "controller for working with employees")
@RestController
@RequiredArgsConstructor
@Validated
public class EmployeeController {

    private final EmployeeFacade employeeFacade;

    @Operation(
            summary = "View employee information",
            description = "Allows to get information of the employee")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The information of the employee has successfully returned",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid employee id",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Employee not found",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content)
            })
    @GetMapping("/{uuid}")
    public ResponseEntity<EmployeeInformationDto> getEmployeeInformationByUuid(
            @PathVariable
            @Pattern(regexp = UUID_PATTERN) String uuid) {
        return ResponseEntity.ok(employeeFacade.getEmployeeInformationByUuid(uuid));
    }

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
            @PathVariable(value = "employee_uuid") String uuid,
            @Valid
            @RequestBody EmployeeUpdateDto dto
    ) {
        employeeFacade.updateEmployeeInfo(dto, uuid);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Add new employee",
            description = "Allow to add new employee"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Adding new employee was successfully compleate",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "400",
                            description = "BAD REQUEST",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "404",
                            description = "NOT FOUND",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Conflict",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "500",
                            description = "INTERNAL SERVER ERROR",
                            content = @Content)
            })
    @PostMapping("/")
    public ResponseEntity<Void> createNewEmployee(
            @Valid
            @Parameter(description = "Employee registered dto")
            @RequestBody EmployeeCreateDto employeeDto){
            employeeFacade.createNewEmployee(employeeDto);
        return ResponseEntity.ok().build();
    }
}
