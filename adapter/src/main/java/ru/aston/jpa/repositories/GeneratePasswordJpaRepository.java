package ru.aston.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.model.GeneratePassword;

@Repository
public interface GeneratePasswordJpaRepository extends JpaRepository<GeneratePassword, Long> {
}
