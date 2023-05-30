package ru.aston.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.aston.dto.request.EmployeeUpdateDto;
import ru.aston.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mappings({
            @Mapping(source = "employeeUpdateDto.passport", target = "passportId"),
            @Mapping(source = "employeeUpdateDto.passportDateIssue", target = "passportDateIssue", dateFormat = "dd.MM.yyyy")
    })
    Employee mapEmployeeUpdateDtoToEmployee(EmployeeUpdateDto employeeUpdateDto);
}
