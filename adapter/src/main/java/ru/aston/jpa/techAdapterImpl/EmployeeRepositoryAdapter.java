package ru.aston.jpa.techAdapterImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.aston.app.api.repositories.EmployeeRepository;
import ru.aston.exception.EmployeeNotFoundByPassportIdException;
import ru.aston.exception.EmployeeNotFoundException;
import ru.aston.jpa.repositories.EmployeeJpaRepository;
import ru.aston.model.Employee;

import java.util.UUID;

/**
 * EmployeeRepositoryAdapter that implements EmployeeRepository and throws specific exceptions.
 * Uses EmployeeJpaRepisitory methods inside
 *
 * @see EmployeeRepository
 * @see EmployeeJpaRepository
 */
@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryAdapter implements EmployeeRepository {

    private final EmployeeJpaRepository employeeJpaRepository;

    @Override
    public Employee findEmployeeByUuid(UUID uuid) {
        return employeeJpaRepository.findByUuid(uuid)
                .orElseThrow(() -> new EmployeeNotFoundException(uuid));
    }

    @Override
    public void save(Employee employee) {
        employeeJpaRepository.save(employee);
    }

    @Override
    public Employee findEmployeeByLogin(String login) {
        return employeeJpaRepository.findEmployeeByLogin(login)
                .orElseThrow(() -> new EmployeeNotFoundException(login));
    }

    @Override
    public Employee findEmployeeByPassportId(String passportId) {
        return employeeJpaRepository.findEmployeeByPassportId(passportId)
                .orElseThrow(()-> new EmployeeNotFoundByPassportIdException(passportId));
    }

    @Override
    public boolean existByUuid(UUID uuid){
        return employeeJpaRepository.existsByUuid(uuid);
    }

    @Override
    public boolean existByLogin(String login) {
        return employeeJpaRepository.existsByLogin(login);
    }

    @Override
    public boolean existByPassportId(String passportId) {
        return employeeJpaRepository.existsByPassportId(passportId);
    }

}
