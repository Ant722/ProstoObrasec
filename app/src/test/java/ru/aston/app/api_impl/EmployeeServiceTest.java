package ru.aston.app.api_impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.app.api.repositories.EmployeeRepository;
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

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Test
    public void createNewEmployee_ShouldOK_WhenEmployeeValid(){
        Employee employee = EmployeeFactory.getValidEmployee();
        when(employeeRepository.findEmployeeByLogin(any())).thenReturn(null);
        when(employeeRepository.findEmployeeByPassportId(any())).thenReturn(null);
        employeeService.createNewEmployee(employee);
        verify(employeeRepository, times(1)).findEmployeeByPassportId(any());
        verify(employeeRepository, times(1)).findEmployeeByLogin(any());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void createNewEmployee_ShouldThrowLogicConflictEx_WhenLoginIsExist(){
        Employee employee = EmployeeFactory.getInvalidEmployee();
        doThrow(LoginConflictException.class).when(employeeRepository).findEmployeeByLogin(any());
        assertThrows(LoginConflictException.class, ()->employeeService.createNewEmployee(employee));
    }

    @Test
    public void createNewEmployee_ShouldThrowPassportConflictEx_WhenPassportExist(){
        Employee employee = EmployeeFactory.getInvalidEmployee();
        doThrow(PassportIdConflictException.class).when(employeeRepository).findEmployeeByLogin(any());
        assertThrows(PassportIdConflictException.class, ()->employeeService.createNewEmployee(employee));
    }
}
