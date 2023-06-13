package ru.aston.app.api.repositories;

import ru.aston.model.GeneratePassword;

public interface GeneratePasswordRepository {

    void save(GeneratePassword password);
}
