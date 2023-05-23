package ru.aston.rest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.dto.request.EmployeeEditDto;
import ru.aston.facade.EmployeeFacade;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeFacade employeeFacade;

    @PutMapping("/employee/{employee_id}")
    public ResponseEntity<Void> editEmployee(
            @PathVariable(value = "employee_id") Long employeeId,
            @Valid
            @RequestBody EmployeeEditDto dto
            ) {
        System.out.println("*********DTO*********\n" + dto);
        employeeFacade.updateEmployeeInfo(dto, employeeId);
        return ResponseEntity.ok().build();
    }
}