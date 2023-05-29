package ru.aston.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aston.dto.response.EmployeeInformationDto;
import ru.aston.model.Employee;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    String DATE_PATTERN_FORMAT = "dd.MM.yyyy";

    @Mapping(source = "passportDateIssue", target = "passportDateIssue", dateFormat = "dd.MM.yyyy")
    EmployeeInformationDto mapEmployeeToEmployeeInformationDto(Employee employee);

    default String mapInstantToStringDate(Instant instant) {
        return DateTimeFormatter.ofPattern(DATE_PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault())
                .format(instant);
    }
}
