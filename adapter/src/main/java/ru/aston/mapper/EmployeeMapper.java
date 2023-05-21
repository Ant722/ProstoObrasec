package ru.aston.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.aston.dto.request.EmployeeEditDto;
import ru.aston.model.Employee;
import ru.aston.model.enumeration.EmployeeRole;
import ru.aston.model.enumeration.EmployeeStatus;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mappings({
            @Mapping(source = "employeeEditDto.employeeId", target = "id"),
            @Mapping(source = "employeeEditDto.statusId", target = "status.name", qualifiedByName = "mapStatus"),
            @Mapping(source = "employeeEditDto.roleId", target = "role.name", qualifiedByName = "employeeRole"),
            @Mapping(source = "employeeEditDto.passport", target = "passportId"),
            @Mapping(source = "employeeEditDto.passportDateIssue", target = "passportDateIssue", dateFormat = "dd.MM.yyyy")
    })
    Employee mapEmployeeEditDtoToEmployee(EmployeeEditDto employeeEditDto);

    @Named("mapStatus")
    default EmployeeStatus mapStatus(Integer value) {
        if (value - 1 > EmployeeStatus.values().length) {
            throw new RuntimeException("Status id is invalid");
            // todo replace with A-money Exception
        }
        EmployeeStatus[] employeeStatuses = EmployeeStatus.values();
        return employeeStatuses[value - 1];
    }

    @Named("employeeRole")
    default EmployeeRole mapRole(Integer value) {
        if (value - 1 > EmployeeRole.values().length) {
            throw new RuntimeException("Role id is invalid");
            // todo replace with A-money Exception
        }
        EmployeeRole[] employeeRoles = EmployeeRole.values();
        return employeeRoles[value - 1];
    }
}
