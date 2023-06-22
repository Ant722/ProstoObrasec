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
import ru.aston.app.api_impl.MailServiceImpl;
import ru.aston.appimpl.serviceimpl.entity_factory.EmployeeFactory;
import ru.aston.exception.LoginConflictException;
import ru.aston.exception.PassportIdConflictException;
import ru.aston.exception.PasswordGenerateTimeException;
import ru.aston.model.Employee;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private MailServiceImpl mailService;

    private static final UUID UUID_EMPLOYEE = UUID.fromString("fe0d0744-fe15-11ed-be56-0242ac120002");

    @Test
    void generatePasswordByUuidMustNewPassword() {
        Employee employee = EmployeeFactory.getValidEmployee();
        String oldPassword = employee.getGeneratePassword().getPassword();
        Mockito.when(employeeRepository.findEmployeeByUuid(UUID_EMPLOYEE))
                .thenReturn(employee);
        Employee expectedEmployee = employeeService.generatePasswordByUuid(UUID_EMPLOYEE);
        Assertions.assertNotEquals(oldPassword, expectedEmployee.getGeneratePassword().getPassword());
    }

    @Test
    void generatePasswordByUuidShouldGenerateTimeException() {
        Employee employee = EmployeeFactory.getEmployeeWrongPasswordGenerationDate();
        Mockito.when(employeeRepository.findEmployeeByUuid(UUID_EMPLOYEE)).thenReturn(employee);
        Assertions.assertThrows(PasswordGenerateTimeException.class,
                () -> employeeService.generatePasswordByUuid(UUID_EMPLOYEE));
    }
    @Test
    public void createNewEmployee_ShouldOK_WhenEmployeeValid(){
        Employee employee =EmployeeFactory.getValidEmployee();
        when(employeeRepository.existByUuid(any())).thenReturn(false);
        when(employeeRepository.existByLogin(any())).thenReturn(false);
        when(employeeRepository.existByPassportId(any())).thenReturn(false);
        employeeService.createNewEmployee(employee);
        verify(employeeRepository, times(1)).existByUuid(any());
        verify(employeeRepository, times(1)).existByLogin(any());
        verify(employeeRepository, times(1)).existByPassportId(any());
        verify(employeeRepository, times(1)).save(employee);
        verify(mailService, times(1)).sendSimpleEmailFromGeneratePassword(any());
    }

    @Test
    public void createNewEmployee_ShouldThrowLogicConflictEx_WhenLoginIsExist(){
        Employee employee = EmployeeFactory.getInvalidEmployee();
        doThrow(LoginConflictException.class).when(employeeRepository).existByLogin(any());
        assertThrows(LoginConflictException.class, ()->employeeService.createNewEmployee(employee));
    }

    @Test
    public void createNewEmployee_ShouldThrowPassportConflictEx_WhenPassportExist(){
        Employee employee = EmployeeFactory.getInvalidEmployee();
        doThrow(PassportIdConflictException.class).when(employeeRepository).existByPassportId(any());
        assertThrows(PassportIdConflictException.class, ()->employeeService.createNewEmployee(employee));
    }
}
