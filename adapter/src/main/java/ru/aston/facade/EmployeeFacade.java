package ru.aston.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aston.app.api.services.EmployeeService;
import ru.aston.dto.responce.EmployeeInformationDto;
import ru.aston.mapper.EmployeeMapper;

@RequiredArgsConstructor
@Component
public class EmployeeFacade {

    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;

    public EmployeeInformationDto getEmployeeInformationById(Long id) {
        return employeeMapper.mapEmployeeToEmployeeInformationDto(employeeService.getEmployeeById(id));
    }
}
