package com.adesso.generics.jooq;

import org.jooq.UpdatableRecord;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface JooqRepositoryImpl<T, R extends UpdatableRecord<R>, ID extends Serializable> {

    T save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    T update(ID id, T entity);

    void delete(T entity);

    void deleteById(ID id);
}
