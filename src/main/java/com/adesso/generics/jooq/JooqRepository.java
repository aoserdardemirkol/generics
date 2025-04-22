package com.adesso.generics.jooq;

import com.adesso.generics.annotations.JQField;
import com.adesso.generics.annotations.JQId;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Table;
import org.jooq.UpdatableRecord;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

// neden jooq kullandın?
@Repository
public class JooqRepository<T, R extends UpdatableRecord<R>, ID extends Serializable> implements JooqRepositoryImpl<T, R, ID> {

    private static final String ID_MUST_NOT_BE_NULL = "The given id must not be null";
    private static final String IDS_MUST_NOT_BE_NULL = "Ids must not be null";
    private static final String ENTITY_MUST_NOT_BE_NULL = "Entity must not be null";
    private static final String ENTITIES_MUST_NOT_BE_NULL = "Entities must not be null";

    private final DSLContext dsl;
    private final Class<T> entityClass;
    private final Table<R> table;
    private final Class<R> recordClass;

    public JooqRepository(DSLContext dsl, Class<T> entityClass, Table<R> table, Class<R> recordClass) {
        this.dsl = dsl;
        this.entityClass = entityClass;
        this.table = table;
        this.recordClass = recordClass;
    }

    @Override
    public T save(T entity) {
        R record = createRecordFromEntity(entity);
        record.attach(dsl.configuration());
        record.store();
        return fromRecord(record);
    }

    @Override
    public Optional<T> findById(ID id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);

        return Optional.ofNullable(dsl.selectFrom(table)
                .where(getId().eq(id))
                .fetchOneInto(entityClass));
    }

    @Override
    public List<T> findAll() {
        return dsl.selectFrom(table)
                .fetchInto(entityClass);
    }

    public List<T> findAllById(Iterable<ID> ids) {
        Assert.notNull(ids, IDS_MUST_NOT_BE_NULL);

        List<T> results = new ArrayList<>();

        for (ID id : ids) {
            findById(id).ifPresent(results::add);
        }

        return results;
    }

    public List<T> findAllByIdBatch(Collection<? extends ID> ids) {
        Assert.notNull(ids, IDS_MUST_NOT_BE_NULL);

        return dsl.selectFrom(table)
                .where(getId().in(ids))
                .fetchInto(entityClass);
    }

    @Override
    public T update(ID id, T entity) {
        try {
            R existingRecord = dsl.selectFrom(table)
                    .where(getId().eq(id))
                    .fetchOne();

            if (existingRecord == null) {
                throw new IllegalAccessException("Record not found");
            }

            for (java.lang.reflect.Field field : entityClass.getDeclaredFields()) {
                field.setAccessible(true);
                // kullanmadan nasıl yapılabilir
                JQField dbFieldAnnotation = field.getAnnotation(JQField.class);
                if (dbFieldAnnotation != null) {
                    if (field.isAnnotationPresent(JQId.class)) {
                        continue;
                    }

                    Field<Object> columnField = DSL.field(DSL.name(dbFieldAnnotation.value()));
                    existingRecord.set(columnField, field.get(entity));
                }
            }

            existingRecord.attach(dsl.configuration());
            existingRecord.update();

            return fromRecord(existingRecord);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(ID id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);
        dsl.deleteFrom(table)
                .where(getId().eq(id))
                .execute();
    }

    @Override
    public void delete(T entity) {
        try {
            Assert.notNull(entity, ENTITY_MUST_NOT_BE_NULL);

            java.lang.reflect.Field field = getEntityField();
            field.setAccessible(true);
            deleteById((ID) field.get(entity));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll(Iterable<? extends T> entities) {
        Assert.notNull(entities, ENTITIES_MUST_NOT_BE_NULL);

        for (T entity : entities) {
            delete(entity);
        }
    }

    public void deleteAllById(Iterable<? extends ID> ids) {
        Assert.notNull(ids, IDS_MUST_NOT_BE_NULL);
        for (ID id : ids) {
            deleteById(id);
        }
    }

    public void deleteAllByIdBatch(Collection<? extends ID> ids) {
        dsl.deleteFrom(table)
                .where(getId().in(ids))
                .execute();
    }

    private Field<ID> getId() {
        java.lang.reflect.Field field = getEntityField();
        String columnName = field.getName();
        return (Field<ID>) table.field(DSL.name(columnName), field.getType());
    }

    private java.lang.reflect.Field getEntityField() {
        return Arrays.stream(entityClass.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(JQId.class))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No ID field found"));
    }

    private R createRecordFromEntity(T entity) {
        try {
            R record = recordClass.getDeclaredConstructor().newInstance();
            for (java.lang.reflect.Field field : entityClass.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(JQField.class)) {
                    JQField dbFieldAnnotation = field.getAnnotation(JQField.class);
                    Field<Object> columnField = DSL.field(DSL.name(dbFieldAnnotation.value()));
                    record.set(columnField, field.get(entity));
                }
            }
            return record;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException e) {
            throw new RuntimeException("Error creating record from entity", e);
        }
    }

    private T fromRecord(R record) {
        try {
            T entity = entityClass.getDeclaredConstructor().newInstance();

            for (java.lang.reflect.Field field : entityClass.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(JQField.class)) {
                    JQField dbFieldAnnotation = field.getAnnotation(JQField.class);
                    String columnName = dbFieldAnnotation.value();
                    Object value = record.get(DSL.field(DSL.name(columnName)));
                    field.set(entity, value);
                }
            }
            return entity;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException e) {
            throw new RuntimeException("Error converting from record to entity", e);
        }
    }
}

