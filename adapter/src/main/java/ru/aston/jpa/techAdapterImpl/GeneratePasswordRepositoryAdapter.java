package ru.aston.jpa.techAdapterImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.aston.repositories.GeneratePasswordRepository;
import ru.aston.jpa.repositories.GeneratePasswordJpaRepository;
import ru.aston.model.GeneratePassword;

/**
 * GeneratePasswordRepositoryAdapter that implements GeneratePasswordRepository and throws specific exceptions.
 * Uses GeneratePasswordJpaRepository methods inside
 *
 * @see GeneratePasswordRepository
 * @see GeneratePasswordJpaRepository
 */
@Repository
@RequiredArgsConstructor
public class GeneratePasswordRepositoryAdapter implements GeneratePasswordRepository {

    private final GeneratePasswordJpaRepository generatePasswordJpaRepository;
    @Override
    public void save(GeneratePassword password) {
        generatePasswordJpaRepository.save(password);
    }
}
