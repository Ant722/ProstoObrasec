package ru.aston.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.facade.EmployeeFacade;

@RequestMapping("api/v1/admin/employee")
@Tag(name = "Admin Controller", description = "controller for for the admin")
@RestController
@RequiredArgsConstructor
@Validated
public class AdminController {

    private final EmployeeFacade employeeFacade;

    @PutMapping("/{employeeId}/password")
    public void generateNewPassword(@PathVariable String employeeId) {
        employeeFacade.generatePasswordEmployee(employeeId);
    }
}
