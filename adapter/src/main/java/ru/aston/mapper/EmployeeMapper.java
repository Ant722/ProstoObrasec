package ru.aston.mapper;

import org.mapstruct.*;
import ru.aston.dto.request.EmployeeEditDto;
import ru.aston.model.Employee;

/**Used for mapping data from different EmployeeDTO to Employee
 * @see EmployeeEditDto
 * @see Employee*/
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    /**EmployeeEditDTO to Employee fields mapper*/
    @Mappings({
            @Mapping(source = "employeeEditDto.passport", target = "passportId"),
            @Mapping(source = "employeeEditDto.passportDateIssue", target = "passportDateIssue", dateFormat = "dd.MM.yyyy")
    })
    Employee mapEmployeeEditDtoToEmployee(EmployeeEditDto employeeEditDto);
}
