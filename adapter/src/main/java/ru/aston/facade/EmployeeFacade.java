package ru.aston.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aston.app.api.services.EmployeeService;
import ru.aston.dto.request.EmployeeSearchCriteriaDto;
import ru.aston.dto.response.EmployeeInformationDto;
import ru.aston.dto.response.SearchEmployeeResultDto;
import ru.aston.mapper.EmployeeMapper;
import ru.aston.mapper.SearchCriteriaMapper;
import ru.aston.request.EmployeeSearchCriteria;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class EmployeeFacade {

    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;
    private final SearchCriteriaMapper searchCriteriaMapper;

    public EmployeeInformationDto getEmployeeInformationByUuid(String uuid) {
        return employeeMapper.mapEmployeeToEmployeeInformationDto(
                employeeService.getEmployeeByUuid(UUID.fromString(uuid)));
    }

    public SearchEmployeeResultDto searchEmployeesByUsername(EmployeeSearchCriteriaDto dto) {
        EmployeeSearchCriteria searchCriteria = searchCriteriaMapper
                .mapEmployeeSearchCriteriaDtoToEmployeeSearchCriteriaRequest(dto);
        return employeeMapper.mapPageToSearchEmployeeResultDto(
                employeeService.searchEmployeesByUsername(searchCriteria));
    }
}
