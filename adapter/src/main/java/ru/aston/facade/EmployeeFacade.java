package ru.aston.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aston.app.services.EmployeeService;
import ru.aston.dto.request.EmployeeUpdateDto;
import ru.aston.mapper.EmployeeMapper;
import ru.aston.model.Employee;

import java.util.UUID;

/**
 * Contains different logics for operations with Employee between controllers and services
 *
 * @see EmployeeService
 */
@Component
@RequiredArgsConstructor
public class EmployeeFacade {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    /**
     * Accepts EmployeeUpdateDto and Employee uuid. Maps it to Employee and calls Employee update method from service
     */
    public void updateEmployeeInfo(EmployeeUpdateDto employeeUpdateDto, UUID uuid) {
        Employee employee = employeeMapper.mapEmployeeUpdateDtoToEmployee(employeeUpdateDto);
        employeeService.updateEmployeeInfo(employee, uuid);
    }
}
