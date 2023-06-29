package ru.aston.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.dto.request.EmployeeCreateDto;
import ru.aston.dto.request.EmployeeRoleDto;
import ru.aston.dto.request.EmployeeStatusDto;
import ru.aston.dto.request.EmployeeUpdateDto;
import ru.aston.dto.response.EmployeeAuthInfoResponseDto;
import ru.aston.dto.response.EmployeeInformationDto;
import ru.aston.model.Employee;
import ru.aston.model.GeneratePassword;
import ru.aston.model.enumeration.EmployeeRole;
import ru.aston.model.enumeration.EmployeeStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeMapperTest {

    private final String DEFAULT_UUID = "b154a2ce-f576-11ed-a05b-0242ac120003";
    private final String DEFAULT_NAME = "Ivan3";
    private final String DEFAULT_SURNAME = "Ivanov";
    private final String DEFAULT_MIDDLENAME = "Ivanovich";
    private final String DEFAULT_LOGIN = "employeeLogin";
    private final String TEST_NAME = "testName";
    private final String TEST_SURNAME = "testSurname";
    private final String TEST_MIDDLENAME = "testMiddleName";
    private final String TEST_LOGIN = "testLogin";
    private final String TEST_PASSPORT_ID = "testPassportID";
    private final String DEFAULT_PASSPORT_ID = "100";
    private final String DEFAULT_PASSWORD = "password";
    private final String PASSPORT_DATE_ISSUE = "01.01.2023";
    private final String DEFAULT_ROLE = "ADMIN";
    private final String DEFAULT_STATUS = "ACTIVE";
    private final String CREATED_AT = "02.02.2023";
    private final String MODIFIED_AT = "03.03.2023";

    @InjectMocks
    private EmployeeMapperImpl employeeMapper;

    @Test
    void should_mapEmployee_toEmployeeInformationDto() {
        EmployeeInformationDto expectedDto = getExpectedEmployeeInformationDto();
        EmployeeInformationDto actualDto = employeeMapper.mapEmployeeToEmployeeInformationDto(getEmployee());

        assertEquals(expectedDto, actualDto);
    }

    @Test
    void should_mapEmployee_toLoginResponseDto(){
        EmployeeAuthInfoResponseDto expectedDto = getExpectedLoginResponseDto();
        EmployeeAuthInfoResponseDto actualDto =
                employeeMapper.mapEmployeeToResponseDto(getEmployeeWithPassword(), getEmployeeWithPassword().getGeneratePassword());
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void should_mapEmployeeUpdateDto_toEmployee() {
        Employee expectedEmployee = getEmployee();
        expectedEmployee.setId(null);
        expectedEmployee.setUuid(null);
        expectedEmployee.setCreatedAt(null);
        expectedEmployee.setModifiedAt(null);
        Employee actualEmployee = employeeMapper.mapEmployeeUpdateDtoToEmployee(getEmployeeUpdateDto());

        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void should_mapEmployeeCreateDto_toEmployee(){
        Employee expectedEmployee = getExpectedEmployee();
        Employee employee = employeeMapper.mapEmployeeCreateDtoToEmployee(getCreateEmployeeDto());

        Assertions.assertEquals(expectedEmployee,employee);
    }

    private Employee getEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setUuid(UUID.fromString(DEFAULT_UUID));
        employee.setSurname(DEFAULT_SURNAME);
        employee.setName(DEFAULT_NAME);
        employee.setMiddleName(DEFAULT_MIDDLENAME);
        employee.setLogin(DEFAULT_LOGIN);
        employee.setPassportId(DEFAULT_PASSPORT_ID);
        employee.setPassportDateIssue(LocalDate.of(2023, 1, 1));
        employee.setRole(EmployeeRole.ADMIN);
        employee.setStatus(EmployeeStatus.ACTIVE);
        employee.setCreatedAt(LocalDateTime.of(2023, 2, 2, 0, 0));
        employee.setModifiedAt(LocalDateTime.of(2023, 3, 3, 0, 0));
        return employee;
    }

    private Employee getEmployeeWithPassword() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setUuid(UUID.fromString(DEFAULT_UUID));
        employee.setSurname(DEFAULT_SURNAME);
        employee.setName(DEFAULT_NAME);
        employee.setMiddleName(DEFAULT_MIDDLENAME);
        employee.setLogin(DEFAULT_LOGIN);
        employee.setPassportId(DEFAULT_PASSPORT_ID);
        employee.setGeneratePassword(GeneratePassword.builder().password(DEFAULT_PASSWORD).build());
        employee.setPassportDateIssue(LocalDate.of(2023, 1, 1));
        employee.setRole(EmployeeRole.ADMIN);
        employee.setStatus(EmployeeStatus.ACTIVE);
        employee.setCreatedAt(LocalDateTime.of(2023, 2, 2, 0, 0));
        employee.setModifiedAt(LocalDateTime.of(2023, 3, 3, 0, 0));
        return employee;
    }

    private EmployeeInformationDto getExpectedEmployeeInformationDto() {
        EmployeeInformationDto dto = new EmployeeInformationDto();
        dto.setSurname(DEFAULT_SURNAME);
        dto.setName(DEFAULT_NAME);
        dto.setMiddleName(DEFAULT_MIDDLENAME);
        dto.setLogin(DEFAULT_LOGIN);
        dto.setPassportId(DEFAULT_PASSPORT_ID);
        dto.setPassportDateIssue(PASSPORT_DATE_ISSUE);
        dto.setRole(DEFAULT_ROLE);
        dto.setStatus(DEFAULT_STATUS);
        dto.setCreatedAt(CREATED_AT);
        dto.setModifiedAt(MODIFIED_AT);
        return dto;
    }

    private EmployeeUpdateDto getEmployeeUpdateDto() {
        EmployeeUpdateDto dto = new EmployeeUpdateDto();
        dto.setSurname(DEFAULT_SURNAME);
        dto.setName(DEFAULT_NAME);
        dto.setMiddleName(DEFAULT_MIDDLENAME);
        dto.setLogin(DEFAULT_LOGIN);
        dto.setPassport(DEFAULT_PASSPORT_ID);
        dto.setPassportDateIssue(PASSPORT_DATE_ISSUE);
        dto.setRole(new EmployeeRoleDto(DEFAULT_ROLE));
        dto.setStatus(new EmployeeStatusDto(DEFAULT_STATUS));
        return dto;
    }

    private EmployeeCreateDto getCreateEmployeeDto(){
        EmployeeCreateDto newRegisteredEmployee = new EmployeeCreateDto();
        newRegisteredEmployee.setName(TEST_NAME);
        newRegisteredEmployee.setSurname(TEST_SURNAME);
        newRegisteredEmployee.setMiddleName(TEST_MIDDLENAME);
        newRegisteredEmployee.setRole(new EmployeeRoleDto(DEFAULT_ROLE));
        newRegisteredEmployee.setStatus(new EmployeeStatusDto(DEFAULT_STATUS));
        newRegisteredEmployee.setPassportDateIssue(PASSPORT_DATE_ISSUE);
        newRegisteredEmployee.setPassportId(TEST_PASSPORT_ID);
        newRegisteredEmployee.setLogin(TEST_LOGIN);
        return newRegisteredEmployee;
    }

    private Employee getExpectedEmployee(){
        Employee employee = new Employee();
        employee.setName(TEST_NAME);
        employee.setSurname(TEST_SURNAME);
        employee.setMiddleName(TEST_MIDDLENAME);
        employee.setRole(EmployeeRole.ADMIN);
        employee.setStatus(EmployeeStatus.ACTIVE);
        employee.setPassportDateIssue(LocalDate.of(2002,9,21));
        employee.setPassportId(TEST_PASSPORT_ID);
        employee.setLogin(TEST_LOGIN);
        return employee;
    }

    private EmployeeAuthInfoResponseDto getExpectedLoginResponseDto(){
        EmployeeAuthInfoResponseDto dto = new EmployeeAuthInfoResponseDto();
        dto.setUuid(DEFAULT_UUID);
        dto.setPassword(DEFAULT_PASSWORD);
        dto.setRole(DEFAULT_ROLE);
        return dto;
    }
}
