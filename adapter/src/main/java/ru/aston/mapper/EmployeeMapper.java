package ru.aston.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.dto.response.EmployeeInformationDto;
import ru.aston.model.Employee;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static ru.aston.util.MappingPatterns.DATE_PATTERN_FORMAT;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "role", target = "role")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "passportDateIssue", target = "passportDateIssue", dateFormat = "dd.MM.yyyy")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "modifiedAt", target = "modifiedAt")
    EmployeeInformationDto mapEmployeeToEmployeeInformationDto(Employee employee);

    default String mapInstantToStringDate(Instant instant) {
        return DateTimeFormatter.ofPattern(DATE_PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault())
                .format(instant);
    }
}
