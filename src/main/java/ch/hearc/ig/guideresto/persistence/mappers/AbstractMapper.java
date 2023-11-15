package ch.hearc.ig.guideresto.persistence.mappers;

import ch.hearc.ig.guideresto.persistence.cache.Cache;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Abstract class that implements the common methods for all mappers
 * @param <T> The type of the data class that the mapper is responsible for
 */
public abstract class AbstractMapper<T> implements Mapper<T> {

    /**
     * This method needs to be implemented for each mapper
     * The method is responsible to extract data from result set and create the
     * mapper corresponding data class
     */
    protected abstract T mapToObj(ResultSet rs) throws SQLException;

    /**
     * This method maps the result set to an object
     * @param rs The result set to map
     * @return An optional containing the mapped object if the result set contains data
     * @throws SQLException If the result set is empty
     */
    protected Optional<T> map(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return Optional.of(mapToObj(rs));
        }
        return Optional.empty();
    }

    /**
     * This method maps the result set to a set of objects
     * @param rs The result set to map
     * @return A set containing the mapped objects
     * @throws SQLException If the result set is empty
     */
    protected Set<T> mapAll(ResultSet rs) throws SQLException {
        var allData = new HashSet<T>();
        Optional<T> data;
        while ((data = map(rs)).isPresent()) {
            allData.add(data.get());
        }
        return allData;
    }

    /**
     * This method use the provided mapper to follow relations between entities
     */
    protected <E> E getToOneRelation(Mapper<E> mapper, int value) {
        return mapper.find(value).orElseThrow();
    }

    protected <E> Set<E> getToManyRelation(Mapper<E> mapper, String field, String value) {
        return mapper.getWhere(field, value);
    }
}
