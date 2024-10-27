package ru.clevertec.edu.ykv.service;

import java.util.List;
import java.util.UUID;

public interface Service<K, V> {

    K save(V entity);

    K findByUuid(UUID uuid);

    List<K> findAll();

    K update(UUID entityUuid, V entity);

    void delete(UUID entityUuid);

}
