package ru.aston.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aston.app.services.EmployeeService;

@Component
@RequiredArgsConstructor
public class EmployeeFacade {
    private EmployeeService employeeService;
}
