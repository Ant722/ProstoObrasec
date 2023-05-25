package ru.aston.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.dto.request.EmployeeEditDto;
import ru.aston.facade.EmployeeFacade;

@Tag(name = "Employee edit controller", description = "Controller edits employee data in DB")
@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class EmployeeEditController {

    private final EmployeeFacade employeeFacade;

    @Operation(
            summary = "Employee editing"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Employee successfully edited",
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
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content)}
    )

    @PutMapping("/employee/{employee_id}")
    public ResponseEntity<Void> editEmployee(
            @PathVariable(value = "employee_id") Long employeeId,
            @Valid
            @RequestBody EmployeeEditDto dto
            ) {
        employeeFacade.updateEmployeeInfo(dto, employeeId);
        return ResponseEntity.ok().build();
    }
}