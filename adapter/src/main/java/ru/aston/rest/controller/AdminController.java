package ru.aston.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.dto.response.PasswordGenerateInfoDto;
import ru.aston.facade.EmployeeFacade;

import static ru.aston.util.ValidationConstants.UUID_PATTERN;

@RequestMapping("api/v1/admin/employee")
@Tag(name = "Admin Controller", description = "controller for for the admin")
@RestController
@RequiredArgsConstructor
@Validated
public class AdminController {

    private final EmployeeFacade employeeFacade;

    @PutMapping("/password/{employeeId}")
    public ResponseEntity<PasswordGenerateInfoDto> generateNewPassword(
            @PathVariable
            @Pattern(regexp = UUID_PATTERN) String employeeId) {
        return ResponseEntity.ok(employeeFacade.generatePasswordEmployeeByUuid(employeeId));
    }
}
