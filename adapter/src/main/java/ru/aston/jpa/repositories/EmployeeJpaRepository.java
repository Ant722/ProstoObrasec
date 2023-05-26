package ru.aston.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.model.Employee;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {
}
