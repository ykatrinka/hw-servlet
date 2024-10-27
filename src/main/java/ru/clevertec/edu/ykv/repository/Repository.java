package ru.clevertec.edu.ykv.repository;

import java.util.List;
import java.util.UUID;

public interface Repository<T> {

    T save(T entity);

    T findByUuid(UUID uuid);

    List<T> findAll();

    T update(UUID entityUuid, T entity);

    void delete(UUID entityUuid);
}
