package ru.aston.jpa.tech_adapter_impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.aston.app.repositories.StatusRepository;
import ru.aston.exception.StatusNotFoundException;
import ru.aston.jpa.repositories.StatusJpaRepository;
import ru.aston.model.Status;

@Repository
@AllArgsConstructor
public class StatusRepositoryAdapter implements StatusRepository {

    private StatusJpaRepository statusJpaRepository;

    @Override
    public Status findStatusById(Integer id) {
        return statusJpaRepository.getStatusById(id)
                .orElseThrow(() -> new StatusNotFoundException(id));
    }
}
