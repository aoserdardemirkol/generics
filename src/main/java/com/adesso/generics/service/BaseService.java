package com.adesso.generics.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, V> {

    T create(T entity);

    Optional<T> getById(V id);

    List<T> getAll();

    T update(Long id, T entity);

    void delete(T entity);

    void deleteById(V id);

    Double calculate();
}
