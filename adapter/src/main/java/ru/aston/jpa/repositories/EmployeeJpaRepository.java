package ru.aston.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.model.Employee;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeByUuid(UUID uuid);

    Optional<Employee> findEmployeeByLogin(String login);
}
