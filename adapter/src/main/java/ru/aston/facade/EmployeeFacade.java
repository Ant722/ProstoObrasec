package ru.aston.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aston.app.services.EmployeeService;
import ru.aston.dto.request.EmployeeEditDto;
import ru.aston.mapper.EmployeeMapper;
import ru.aston.model.Employee;

import java.util.UUID;

/**Contains different logics for operations with Employee between controllers and services
 * @see EmployeeService*/
@Component
@AllArgsConstructor
public class EmployeeFacade {

    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    /**Accepts EmployeeEditDto and Employee uuid. Maps it to Employee and calls Employee update method from service*/
    public void updateEmployeeInfo(EmployeeEditDto employeeEditDto, UUID uuid) {
        Employee employee = employeeMapper.mapEmployeeEditDtoToEmployee(employeeEditDto);
        employeeService.updateEmployeeInfo(employee, uuid);
    }
}
