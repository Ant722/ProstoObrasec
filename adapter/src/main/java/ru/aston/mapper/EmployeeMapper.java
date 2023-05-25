package ru.aston.mapper;

import org.mapstruct.*;
import ru.aston.dto.request.EmployeeEditDto;
import ru.aston.exception.InvalidEmployeeRoleException;
import ru.aston.exception.InvalidEmployeeStatusException;
import ru.aston.model.Employee;
import ru.aston.model.Role;
import ru.aston.model.Status;
import ru.aston.model.enumeration.EmployeeRole;
import ru.aston.model.enumeration.EmployeeStatus;

/**Interface for mapping between Employee instances*/
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    /**Mapping Efields from EmployeeEditDto to Employee. Returns new Employee*/
    @Mappings({
            @Mapping(source = "employeeEditDto.employeeId", target = "id"),
            @Mapping(source = "employeeEditDto.statusId", target = "status", qualifiedByName = "mapStatus"),
            @Mapping(source = "employeeEditDto.roleId", target = "role", qualifiedByName = "employeeRole"),
            @Mapping(source = "employeeEditDto.passport", target = "passportId"),
            @Mapping(source = "employeeEditDto.passportDateIssue", target = "passportDateIssue", dateFormat = "dd.MM.yyyy")
    })
    Employee mapEmployeeEditDtoToEmployee(EmployeeEditDto employeeEditDto);

    /**Additional method for mapping employee status from request to employee*/
    @Named("mapStatus")
    default Status mapStatus(Integer value) {
        if (value - 1 > EmployeeStatus.values().length) {
            throw new InvalidEmployeeStatusException(value);
        }
        EmployeeStatus[] employeeStatuses = EmployeeStatus.values();
        return new Status(value, employeeStatuses[value - 1]);
    }

    /**Additional method for mapping employee role from request to employee*/
    @Named("employeeRole")
    default Role mapRole(Integer value) {
        if (value - 1 > EmployeeRole.values().length) {
            throw new InvalidEmployeeRoleException(value);
        }
        EmployeeRole[] employeeRoles = EmployeeRole.values();
        return new Role(value, employeeRoles[value - 1]);
    }
}
