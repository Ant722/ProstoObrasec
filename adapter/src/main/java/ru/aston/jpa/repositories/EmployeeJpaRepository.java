package ru.aston.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.aston.model.Employee;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository of Employee. Has all necessary simple queries.
 *
 * @see Employee
 */
@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee, Long>, QuerydslPredicateExecutor<Employee> {

    Optional<Employee> findByUuid(UUID uuid);

    Optional<Employee> findEmployeeByLogin(String login);
}
