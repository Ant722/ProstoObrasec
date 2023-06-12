package ru.aston.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.aston.dto.request.EmployeeCreateDto;
import ru.aston.dto.request.EmployeeUpdateDto;
import ru.aston.dto.response.EmployeeInformationDto;
import ru.aston.model.Employee;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    String DATE_PATTERN_FORMAT = "dd.MM.yyyy";

    @Mapping(source = "passportDateIssue", target = "passportDateIssue", dateFormat = DATE_PATTERN_FORMAT)
    EmployeeInformationDto mapEmployeeToEmployeeInformationDto(Employee employee);

    default String mapInstantToStringDate(Instant instant) {
        return DateTimeFormatter.ofPattern(DATE_PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault())
                .format(instant);
    }

    @Mappings({
            @Mapping(source = "employeeCreateDto.passportDateIssue", target = "passportDateIssue", dateFormat = "dd.MM.yyyy"),
    })
    Employee mapEmployeeCreateDtoToEmployee(EmployeeCreateDto employeeCreateDto);


    @Mappings({
            @Mapping(source = "employeeUpdateDto.passport", target = "passportId"),
            @Mapping(source = "employeeUpdateDto.passportDateIssue", target = "passportDateIssue", dateFormat = DATE_PATTERN_FORMAT),
            @Mapping(source = "employeeUpdateDto.statusDto.status", target = "status"),
            @Mapping(source = "employeeUpdateDto.roleDto.role", target = "role")
    })
    Employee mapEmployeeUpdateDtoToEmployee(EmployeeUpdateDto employeeUpdateDto);
}
