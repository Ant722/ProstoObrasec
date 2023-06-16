package ru.aston.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.aston.dto.request.EmployeeUpdateDto;
import org.springframework.data.domain.Page;
import ru.aston.dto.response.EmployeeInformationDto;
import ru.aston.dto.response.EmployeeShortInformationDto;
import ru.aston.dto.response.SearchEmployeeResultDto;
import ru.aston.model.Employee;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring", uses = {PaginationMapper.class})
public interface EmployeeMapper {


    String DATE_PATTERN_FORMAT = "dd.MM.yyyy";

    @Mapping(source = "passportDateIssue", target = "passportDateIssue", dateFormat = DATE_PATTERN_FORMAT)
    EmployeeInformationDto mapEmployeeToEmployeeInformationDto(Employee employee);

    default String mapInstantToStringDate(LocalDateTime localDateTime) {
    EmployeeShortInformationDto mapEmployeeToEmployeeShortInformationDto(Employee employee);

    List<EmployeeShortInformationDto> mapListEmployeeToListEmployeeShortInformationDto(List<Employee> employeeList);

    @Mapping(target = "result", source = "page.content")
    @Mapping(target = "info", source = "page")
    SearchEmployeeResultDto mapPageToSearchEmployeeResultDto(Page<Employee> page);

    default String mapInstantToStringDate(Instant instant) {
        return DateTimeFormatter.ofPattern(DATE_PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault())
                .format(localDateTime);
    }

    @Mappings({
            @Mapping(source = "employeeUpdateDto.passport", target = "passportId"),
            @Mapping(source = "employeeUpdateDto.passportDateIssue", target = "passportDateIssue", dateFormat = DATE_PATTERN_FORMAT),
            @Mapping(source = "employeeUpdateDto.statusDto.status", target = "status"),
            @Mapping(source = "employeeUpdateDto.roleDto.role", target = "role")
    })
    Employee mapEmployeeUpdateDtoToEmployee(EmployeeUpdateDto employeeUpdateDto);
}
