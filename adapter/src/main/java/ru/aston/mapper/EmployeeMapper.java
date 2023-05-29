package ru.aston.mapper;

import org.mapstruct.*;
import ru.aston.dto.request.EmployeeEditDto;
import ru.aston.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mappings({
            @Mapping(source = "employeeEditDto.passport", target = "passportId"),
            @Mapping(source = "employeeEditDto.passportDateIssue", target = "passportDateIssue", dateFormat = "dd.MM.yyyy")
    })
    Employee mapEmployeeEditDtoToEmployee(EmployeeEditDto employeeEditDto);
}
