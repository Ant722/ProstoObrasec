package ru.aston.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.dto.responce.EmployeeInformationDto;
import ru.aston.facade.EmployeeFacade;

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
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeInformationDto> getEmployeeInformationById(@PathVariable @Min(0) Long id) {
        return ResponseEntity.ok(employeeFacade.getEmployeeInformationById(id));
    }
}
