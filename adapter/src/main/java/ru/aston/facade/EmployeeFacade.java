package ru.aston.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aston.app.api.services.EmployeeService;
import ru.aston.dto.response.EmployeeInformationDto;
import ru.aston.mapper.EmployeeMapper;
import ru.aston.model.Employee;

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

    public void generatePasswordEmployee(String uuid) {
        Employee employee = employeeService.generatePasswordByUuid(UUID.fromString(uuid));
        //TODO: сделать отдельный сервис по отправки почты
    }
}
