package ru.aston.app.api_impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.app.api.repositories.EmployeeRepository;
import ru.aston.app.api.repositories.GeneratePasswordRepository;
import ru.aston.app.api_impl.entity_factory.EmployeeFactory;
import ru.aston.exception.LoginConflictException;
import ru.aston.exception.PassportIdConflictException;
import ru.aston.model.Employee;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    GeneratePasswordRepository passwordRepository;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Mock
    MailServiceImpl mailService;

    @Test
    public void createNewEmployee_ShouldOK_WhenEmployeeValid(){
        Employee employee = EmployeeFactory.getValidEmployee();
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
