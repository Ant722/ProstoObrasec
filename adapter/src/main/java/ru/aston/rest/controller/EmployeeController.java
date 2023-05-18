package ru.aston.rest.controller;

import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.aston.dto.request.EmployeeDto;
import ru.aston.facade.EmployeeFacade;

import java.util.UUID;

import static ru.aston.util.ValidationConstants.UUID_PATTERN;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeFacade employeeFacade;

    @PutMapping("/employee/{employee_id}")
    public String editEmployee(
            @PathVariable(value = "employee_id") Long employeeId,
            @RequestBody EmployeeDto dto,
            @Pattern(regexp = UUID_PATTERN)UUID uuid
            ) {
        return "id = " + employeeId;
    }
}