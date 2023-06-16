package ru.aston.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.dto.response.EmployeeInformationDto;
import ru.aston.dto.response.EmployeeShortInformationDto;
import ru.aston.model.Employee;
import ru.aston.model.enumeration.EmployeeRole;
import ru.aston.model.enumeration.EmployeeStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeMapperTest {

    @InjectMocks
    private EmployeeMapperImpl employeeMapper;

    @Test
    void should_mapEmployee_toEmployeeInformationDto() {
        EmployeeInformationDto expectedDto = getExpectedEmployeeInformationDto();
        EmployeeInformationDto actualDto = employeeMapper.mapEmployeeToEmployeeInformationDto(getEmployee());

        Assertions.assertEquals(expectedDto, actualDto);
    }

    @Test
    void should_mapEmployee_toEmployeeShortInformationDto() {
        EmployeeShortInformationDto expectedDto = getExpectedEmployeeShortInformationDto();
        EmployeeShortInformationDto actualDto = employeeMapper.mapEmployeeToEmployeeShortInformationDto(getEmployee());

        Assertions.assertEquals(expectedDto, actualDto);
    }

    private Employee getEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setUuid(UUID.fromString("b154a2ce-f576-11ed-a05b-0242ac120003"));
        employee.setSurname("Ivanov");
        employee.setName("Ivan");
        employee.setMiddleName("Ivanovich");
        employee.setLogin("employeeLogin");
        employee.setPassportId("100");
        employee.setPassportDateIssue(LocalDate.of(2023, 1, 1));
        employee.setRole(EmployeeRole.ADMIN);
        employee.setStatus(EmployeeStatus.ACTIVE);
        employee.setCreatedAt(LocalDateTime.of(2023, 2, 2, 0, 0));
        employee.setModifiedAt(LocalDateTime.of(2023, 3, 3, 0, 0));
        return employee;
    }

    private EmployeeInformationDto getExpectedEmployeeInformationDto() {
        EmployeeInformationDto dto = new EmployeeInformationDto();
        dto.setSurname("Ivanov");
        dto.setName("Ivan");
        dto.setMiddleName("Ivanovich");
        dto.setLogin("employeeLogin");
        dto.setPassportId("100");
        dto.setPassportDateIssue("01.01.2023");
        dto.setRole("ADMIN");
        dto.setStatus("ACTIVE");
        dto.setCreatedAt("02.02.2023");
        dto.setModifiedAt("03.03.2023");
        return dto;
    }

    private EmployeeShortInformationDto getExpectedEmployeeShortInformationDto() {
        EmployeeShortInformationDto dto = new EmployeeShortInformationDto();
        dto.setUuid("b154a2ce-f576-11ed-a05b-0242ac120003");
        dto.setSurname("Ivanov");
        dto.setName("Ivan");
        dto.setMiddleName("Ivanovich");
        dto.setRole("ADMIN");
        dto.setStatus("ACTIVE");
        dto.setCreatedAt("02.02.2023");
        dto.setModifiedAt("03.03.2023");
        return dto;
    }
}
