package ru.aston.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aston.app.api.services.EmployeeService;
import ru.aston.dto.request.EmployeeSearchCriteriaDto;
import ru.aston.dto.response.EmployeeInformationDto;
import ru.aston.dto.response.SearchEmployeeResultDto;
import ru.aston.mapper.EmployeeMapper;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class EmployeeFacade {

    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;

    public EmployeeInformationDto getEmployeeInformationByUuid(String uuid) {
        return employeeMapper.mapEmployeeToEmployeeInformationDto(
                employeeService.getEmployeeByUuid(UUID.fromString(uuid)));
    }

    /**
     * Page numbering starts at number 1
     */
    public SearchEmployeeResultDto searchEmployeesByUsername(EmployeeSearchCriteriaDto dto) {
        return employeeMapper.mapPageToSearchEmployeeResultDto(
                employeeService.searchEmployeesByUsername(
                        dto.getStatus().name(),
                        dto.getRole().name(),
                        dto.getSort(),
                        dto.getSurname(),
                        dto.getPage() - 1));
    }
}
