package ru.aston.facade;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aston.dto.request.EmployeeCreateDto;
import ru.aston.dto.request.EmployeeSearchCriteriaDto;
import ru.aston.services.EmployeeService;
import ru.aston.dto.request.EmployeeUpdateDto;
import ru.aston.dto.request.LoginRequestDto;
import ru.aston.dto.response.EmployeeInformationDto;
import ru.aston.dto.response.PasswordGenerateInfoDto;
import ru.aston.dto.response.UuidResponseDto;
import ru.aston.dto.response.SearchEmployeeResultDto;
import ru.aston.dto.response.EmployeeAuthInfoResponseDto;
import ru.aston.mapper.EmployeeMapper;
import ru.aston.mapper.SearchCriteriaMapper;
import ru.aston.model.Employee;
import ru.aston.request.EmployeeSearchCriteria;
import ru.aston.services.MailService;

import java.util.UUID;


/**
 * Contains different logics for operations with Employee between controllers and services
 *
 * @see EmployeeService
 */
@Component
@RequiredArgsConstructor
public class EmployeeFacade {

    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;
    private final SearchCriteriaMapper searchCriteriaMapper;

    private final MailService mailService;

    public EmployeeInformationDto getEmployeeInformationByUuid(String uuid) {
        return employeeMapper.mapEmployeeToEmployeeInformationDto(
                employeeService.getEmployeeByUuid(UUID.fromString(uuid)));
    }

    @Transactional
    public PasswordGenerateInfoDto generatePasswordEmployeeByUuid(String uuid) {
        Employee employee = employeeService.generatePasswordByUuid(UUID.fromString(uuid));
        mailService.sendSimpleEmailFromGeneratePassword(employee);
        return getPasswordGenerateInfoDto(uuid);
    }

    private PasswordGenerateInfoDto getPasswordGenerateInfoDto(String uuid) {
        return PasswordGenerateInfoDto.builder()
                .message(String.format("The new password has been generated and sent to the %s by e-mail", uuid))
                .build();
    }

    /**
     * Accepts EmployeeUpdateDto and Employee uuid. Maps it to Employee and calls Employee update method from service
     */
    public void updateEmployeeInfo(EmployeeUpdateDto employeeUpdateDto, String uuid) {
        Employee employee = employeeMapper.mapEmployeeUpdateDtoToEmployee(employeeUpdateDto);
        employeeService.updateEmployeeInfo(employee, UUID.fromString(uuid));
    }

    public SearchEmployeeResultDto searchEmployeesByUsername(EmployeeSearchCriteriaDto dto) {
        EmployeeSearchCriteria searchCriteria = searchCriteriaMapper
                .mapEmployeeSearchCriteriaDtoToEmployeeSearchCriteriaRequest(dto);
        return employeeMapper.mapPageToSearchEmployeeResultDto(
                employeeService.searchEmployeesByUsername(searchCriteria));
    }

    public UuidResponseDto createNewEmployee(EmployeeCreateDto employeeCreateDto) {
        Employee employee = employeeMapper.mapEmployeeCreateDtoToEmployee(employeeCreateDto);
        return new UuidResponseDto(employeeService.createNewEmployee(employee));
    }

    /**
     * Accepts LoginRequestDto. Gets Employee from DB by login and maps it to EmployeeAuthInfoResponseDto
     */
    public EmployeeAuthInfoResponseDto getInformationByLogin(LoginRequestDto loginRequestDto) {
        Employee employee =
                employeeService.checkEmployeeByLoginAndPassword(loginRequestDto.getLogin(), loginRequestDto.getPassword());
        return employeeMapper.mapEmployeeToResponseDto(employee, employee.getGeneratePassword());
    }
}
