package ch.hearc.ig.guideresto.persistence.mappers;

import ch.hearc.ig.guideresto.business.City;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public abstract class AbstractMapper<T> implements Mapper<T> {

    protected Optional<T> map(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return Optional.of(mapToObj(rs));
        }
        return Optional.empty();
    }

    protected abstract T mapToObj(ResultSet rs) throws SQLException;

    protected Set<T> mapAll(ResultSet rs) throws SQLException {
        System.out.println("Hello");
        var allData = new HashSet<T>();
        Optional<T> data;
        while ((data = map(rs)).isPresent()) {
            allData.add(data.get());
        }
        return allData;
    }

    protected  <E> E getToOneRelation(Mapper<E> mapper, int value) throws SQLException {
       return mapper.find(value).orElseThrow();
    }
    protected <E> Set<E> getToManyRelation(Mapper<E> mapper, String field, String value) throws SQLException {
       return mapper.getWhere(field, value);
    }
}
