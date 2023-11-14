package ch.hearc.ig.guideresto.persistence.mappers;

import ch.hearc.ig.guideresto.persistence.database.exceptions.DatabaseMapperException;

import java.util.Optional;
import java.util.Set;

public interface Mapper<T> {
    Optional<T> find(int id) throws DatabaseMapperException;
    Set<T> getAll();
    Set<T> getWhere(String column, String value);
    T insert(T entity);
    T update(T entity);
    T delete(T entity);
}
