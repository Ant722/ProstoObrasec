package ru.aston.app.api.repositories;

import ru.aston.model.GeneratePassword;

/**
 * Additional repository for making possible to catch some technical exceptions inside implementation
 * (like EmployeeNotFound etc).
 */
public interface GeneratePasswordRepository {

    void save(GeneratePassword password);
}
