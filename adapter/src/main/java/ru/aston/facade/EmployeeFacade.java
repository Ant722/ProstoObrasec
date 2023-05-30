package ru.aston.facade;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class EmployeeFacade {

    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    /**
     * Accepts EmployeeUpdateDto and Employee uuid. Maps it to Employee and calls Employee update method from service
     */
    public void updateEmployeeInfo(EmployeeUpdateDto employeeUpdateDto, UUID uuid) {
        Employee employee = employeeMapper.mapEmployeeUpdateDtoToEmployee(employeeUpdateDto);
        employeeService.updateEmployeeInfo(employee, uuid);
    }
}
