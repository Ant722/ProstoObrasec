package ru.aston.facade;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.aston.app.api.services.MailService;
import ru.aston.app.api.services.EmployeeService;
import ru.aston.dto.response.EmployeeInformationDto;
import ru.aston.dto.response.PasswordGenerateInfoDto;
import ru.aston.mapper.EmployeeMapper;
import ru.aston.model.Employee;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmployeeFacade {

    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;

    private final MailService mailService;

    public EmployeeInformationDto getEmployeeInformationByUuid(String uuid) {
        return employeeMapper.mapEmployeeToEmployeeInformationDto(
                employeeService.getEmployeeByUuid(UUID.fromString(uuid)));
    }

    @Transactional
    public PasswordGenerateInfoDto generatePasswordEmployeeByUuid(String uuid)   {
        Employee employee = employeeService.generatePasswordByUuid(UUID.fromString(uuid));
        mailService.sendSimpleEmailFromGeneratePassword(employee);
        return getPasswordGenerateInfoDto(uuid);
    }

    private PasswordGenerateInfoDto getPasswordGenerateInfoDto(String uuid) {
        return PasswordGenerateInfoDto.builder()
                .message(String.format("The new password has been generated and sent to the %s by e-mail", uuid))
                .build();
    }
}
