package ru.aston.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aston.app.api.services.EmployeeService;
import ru.aston.dto.response.EmployeeInformationDto;
import ru.aston.mapper.EmployeeMapper;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class EmployeeFacade {

    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;

    public EmployeeInformationDto getEmployeeInformationByUuid(String uuid) {
        return employeeMapper.mapEmployeeToEmployeeInformationDto(
                employeeService.getEmployeeByUuid(UUID.fromString(uuid)));
    }
}
