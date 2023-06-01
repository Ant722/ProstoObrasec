package ru.aston.appimpl.serviceimpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.app.api.repositories.EmployeeRepository;
import ru.aston.app.api_impl.EmployeeServiceImpl;
import ru.aston.appimpl.serviceimpl.entity_factory.EmployeeFactory;
import ru.aston.model.Employee;

import java.util.UUID;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private static final UUID UUID_EMPLOYEE = UUID.fromString("fe0d0744-fe15-11ed-be56-0242ac120002");

    @Test
    void generatePasswordByUuidMustNewPassword() {
        Employee employee = EmployeeFactory.getValidEmployee();
        String oldPassword = employee.getPassword();
        Mockito.when(employeeRepository.findEmployeeByUuid(UUID_EMPLOYEE))
                .thenReturn(employee);
        Employee expectedEmployee = employeeService.generatePasswordByUuid(UUID_EMPLOYEE);
        Assertions.assertNotEquals(oldPassword, expectedEmployee.getPassword());
    }
}
