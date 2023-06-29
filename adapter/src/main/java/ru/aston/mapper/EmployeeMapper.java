package ru.aston.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.aston.dto.request.EmployeeCreateDto;
import org.springframework.data.domain.Page;
import ru.aston.dto.request.EmployeeUpdateDto;
import ru.aston.dto.response.EmployeeInformationDto;
import ru.aston.dto.response.EmployeeShortInformationDto;
import ru.aston.dto.response.SearchEmployeeResultDto;
import ru.aston.dto.response.EmployeeAuthInfoResponseDto;
import ru.aston.model.Employee;
import ru.aston.model.GeneratePassword;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring", uses = {PaginationMapper.class})
public interface EmployeeMapper {

    String DATE_PATTERN_FORMAT = "dd.MM.yyyy";

    @Mapping(source = "passportDateIssue", target = "passportDateIssue", dateFormat = DATE_PATTERN_FORMAT)
    EmployeeInformationDto mapEmployeeToEmployeeInformationDto(Employee employee);

    EmployeeShortInformationDto mapEmployeeToEmployeeShortInformationDto(Employee employee);

    List<EmployeeShortInformationDto> mapListEmployeeToListEmployeeShortInformationDto(List<Employee> employeeList);

    @Mapping(target = "result", source = "page.content")
    @Mapping(target = "info", source = "page")
    SearchEmployeeResultDto mapPageToSearchEmployeeResultDto(Page<Employee> page);

    @Mappings({
            @Mapping(source = "employeeCreateDto.passportDateIssue", target = "passportDateIssue", dateFormat = DATE_PATTERN_FORMAT),
            @Mapping(source = "employeeCreateDto.status.status", target = "status"),
            @Mapping(source = "employeeCreateDto.role.role", target = "role")
    })
    Employee mapEmployeeCreateDtoToEmployee(EmployeeCreateDto employeeCreateDto);


    @Mappings({
            @Mapping(source = "employeeUpdateDto.passport", target = "passportId"),
            @Mapping(source = "employeeUpdateDto.passportDateIssue", target = "passportDateIssue", dateFormat = DATE_PATTERN_FORMAT),
            @Mapping(source = "employeeUpdateDto.status.status", target = "status"),
            @Mapping(source = "employeeUpdateDto.role.role", target = "role")
    })
    Employee mapEmployeeUpdateDtoToEmployee(EmployeeUpdateDto employeeUpdateDto);

    default String mapInstantToStringDate(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern(DATE_PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault())
                .format(localDateTime);
    }


            @Mapping(source = "generatePassword.password", target = "password")
    EmployeeAuthInfoResponseDto mapEmployeeToResponseDto(Employee employee, GeneratePassword generatePassword);
}
