package ch.hearc.ig.guideresto.persistence.mappers;

import ch.hearc.ig.guideresto.business.EvaluationCriteria;
import ch.hearc.ig.guideresto.business.Grade;
import ch.hearc.ig.guideresto.persistence.cache.Cache;
import ch.hearc.ig.guideresto.persistence.database.DatabaseProvider;
import ch.hearc.ig.guideresto.persistence.database.exceptions.DatabaseMapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public class GradeMapper extends AbstractMapper<Grade> {
    @Override
    protected Grade mapToObj(ResultSet rs) throws SQLException {
        var id = rs.getInt("FK_CRIT");
        return new Grade(
                id,
                rs.getInt("NOTE"),
                null,
                Cache.getCacheInstance(EvaluationCriteria.class) // Retrieve the cache
                     .getFirstOr(
                             e -> e.getId() == id, // Try to find in cache
                             () -> getToOneRelation(new EvaluationCriteriaMapper(), id) // Retrieve from db if nothing in cache
                     )
        );
    }

    @Override
    public Optional<Grade> find(int id) throws DatabaseMapperException {
        return Optional.empty();
    }

    @Override
    public Set<Grade> getAll() {
        return null;
    }

    @Override
    public Set<Grade> getWhere(String column, String value) {
        try (var query = DatabaseProvider
                .preparedQueryOf(
                        "select NUMERO, NOTE, FK_COMM, FK_CRIT from NOTES where " + column + " = ?")
        ) {
            return mapAll(query.bind(value).execute());
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query findAll", e);
        }
    }

    @Override
    public Grade insert(Grade entity) {
        return null;
    }

    @Override
    public Grade update(Grade entity) {
        return null;
    }

    @Override
    public Grade delete(Grade entity) {
        return null;
    }
}
