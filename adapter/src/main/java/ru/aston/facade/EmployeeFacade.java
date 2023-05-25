package ru.aston.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aston.app.services.EmployeeService;
import ru.aston.dto.request.EmployeeEditDto;
import ru.aston.mapper.EmployeeMapper;
import ru.aston.model.Employee;

import java.util.UUID;

/**Contains different logics for operations with Employee between controllers and services*/
@Component
@AllArgsConstructor
public class EmployeeFacade {

    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    /**Accepts EmployeeEditDto and Employee id, separates UUID from dto for validating it as admin's.*/
    // todo possible to accept employee's UUID in future (depends of analytics)
    public void updateEmployeeInfo(EmployeeEditDto employeeEditDto, Long employeeId) {
        Employee employee = employeeMapper.mapEmployeeEditDtoToEmployee(employeeEditDto);
        UUID uuidForCheckAdmin = employeeEditDto.getUuid();
        employee.setUuid(null);
        employeeService.updateEmployeeInfo(employee, uuidForCheckAdmin, employeeId);
    }
}
