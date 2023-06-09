package ru.aston.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aston.app.api.services.EmployeeService;
import ru.aston.dto.request.EmployeeUpdateDto;
import ru.aston.dto.response.EmployeeInformationDto;
import ru.aston.mapper.EmployeeMapper;
import ru.aston.model.Employee;

import java.util.UUID;

/**
 * Contains different logics for operations with Employee between controllers and services
 *
 * @see EmployeeService
 */
@RequiredArgsConstructor
@Component
public class EmployeeFacade {

    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;

    public EmployeeInformationDto getEmployeeInformationByUuid(String uuid) {
        return employeeMapper.mapEmployeeToEmployeeInformationDto(
                employeeService.getEmployeeByUuid(UUID.fromString(uuid)));
    }

    /**
     * Accepts EmployeeUpdateDto and Employee uuid. Maps it to Employee and calls Employee update method from service
     */
    public void updateEmployeeInfo(EmployeeUpdateDto employeeUpdateDto, String uuid) {
        Employee employee = employeeMapper.mapEmployeeUpdateDtoToEmployee(employeeUpdateDto);
        employeeService.updateEmployeeInfo(employee, UUID.fromString(uuid));
    }
}
