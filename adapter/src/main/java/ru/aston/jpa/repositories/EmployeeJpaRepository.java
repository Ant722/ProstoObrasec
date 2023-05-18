package ru.aston.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.model.Employee;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeById(Long id);
    Optional<Employee> findEmployeeByUuid(UUID uuid);
    Optional<Employee> findEmployeeByLogin(String login);
}
