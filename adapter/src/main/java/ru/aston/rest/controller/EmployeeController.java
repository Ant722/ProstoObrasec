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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.dto.request.EmployeeSearchCriteriaDto;
import ru.aston.dto.response.EmployeeInformationDto;
import ru.aston.dto.response.SearchEmployeeResultDto;
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
            summary = "Search employees by partial surname match with sort and filter",
            description = "Allows to get information of the employees")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The information of the employees has successfully returned",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request parameters",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content)
            })
    @GetMapping
    public ResponseEntity<SearchEmployeeResultDto> searchEmployeesByUsername(@Valid EmployeeSearchCriteriaDto dto) {
        return ResponseEntity.ok(employeeFacade.searchEmployeesByUsername(dto));
    }
}
