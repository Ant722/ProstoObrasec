package ru.aston.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aston.app.services.EmployeeService;
import ru.aston.dto.request.EmployeeEditDto;
import ru.aston.mapper.EmployeeMapper;
import ru.aston.model.Employee;

import java.util.UUID;

@Component
@AllArgsConstructor
public class EmployeeFacade {

    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    public boolean updateEmployeeInfo(EmployeeEditDto employeeEditDto, Long employeeId) {
        Employee employee = employeeMapper.mapEmployeeEditDtoToEmployee(employeeEditDto);
        UUID uuidForCheckAdmin = employeeEditDto.getUuid();
        employee.setUuid(null);
        return employeeService.updateEmployeeInfo(employee, uuidForCheckAdmin, employeeId);
    }
}
