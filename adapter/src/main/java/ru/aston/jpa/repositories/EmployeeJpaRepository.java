package ru.aston.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;
import ru.aston.model.Employee;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Long>, ListQuerydslPredicateExecutor<Employee> {

    Optional<Employee> findByUuid(UUID uuid);
}
