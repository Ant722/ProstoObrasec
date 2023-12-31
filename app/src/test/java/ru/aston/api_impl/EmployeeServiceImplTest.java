package ru.aston.api_impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.api_impl.entity_factory.EmployeeFactory;
import ru.aston.repositories.EmployeeRepository;
import ru.aston.exception.LoginConflictException;
import ru.aston.services.api_impl.EmployeeServiceImpl;

import java.util.UUID;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    private final UUID EXISTING_UUID = UUID.fromString("6bf20ff0-fa1e-4a2d-ac8a-8609914c575f");

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Test
    void should_throwLoginConflictException_whenLoginConflict() {
        String loginForConflict = EmployeeFactory.getExistedEmployeeForLoginConflict().getLogin();
        when(employeeRepository.findEmployeeByUuid(EXISTING_UUID)).thenReturn(EmployeeFactory.getCorrectEmployee());
        when(employeeRepository.existByLogin(loginForConflict)).thenReturn(true);
        Assertions.assertThrows(LoginConflictException.class, () -> employeeServiceImpl.updateEmployeeInfo(EmployeeFactory.getEmployeeWithLoginConflict(), EXISTING_UUID));
    }
}
