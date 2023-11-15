package ch.hearc.ig.guideresto.persistence.mappers;

import ch.hearc.ig.guideresto.business.BasicEvaluation;
import ch.hearc.ig.guideresto.persistence.database.DatabaseProvider;
import ch.hearc.ig.guideresto.persistence.database.exceptions.DatabaseMapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public class BasicEvaluationMapper extends AbstractMapper<BasicEvaluation> {

    @Override
    protected BasicEvaluation mapToObj(ResultSet rs) throws SQLException {
        return new BasicEvaluation(
                rs.getInt("NUMERO"),
                rs.getDate("DATE_EVAL").toLocalDate(),
                null,
                rs.getString("APPRECIATION").equals("T"),
                rs.getString("ADRESSE_IP"));
    }

    @Override
    public Optional<BasicEvaluation> find(int id) throws DatabaseMapperException {
        return Optional.empty();
    }

    @Override
    public Set<BasicEvaluation> getAll() {
        try (var query = DatabaseProvider
                .preparedQueryOf(
                        "select NUMERO, APPRECIATION, DATE_EVAL, ADRESSE_IP from LIKES")
        ) {
            return mapAll(query.execute());
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query findAll", e);
        }
    }

    @Override
    public Set<BasicEvaluation> getWhere(String column, String value) {
        try (var query = DatabaseProvider
                .preparedQueryOf(
                        "select NUMERO, APPRECIATION, DATE_EVAL, ADRESSE_IP, FK_REST from LIKES where " + column + " = ?")
        ) {
            return mapAll(query.bind(value).execute());
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query findAll", e);
        }
    }

    public Set<BasicEvaluation> getWhere(String column, int value) {
        try (var query = DatabaseProvider
                .preparedQueryOf(
                        "select NUMERO, APPRECIATION, DATE_EVAL, ADRESSE_IP, FK_REST from LIKES where ? = ?")
        ) {
            return mapAll(query.bind(column).bind(value).execute());
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query findAll", e);
        }
    }

    @Override
    public BasicEvaluation insert(BasicEvaluation entity) {
        return null;
    }

    @Override
    public BasicEvaluation update(BasicEvaluation entity) {
        return null;
    }

    @Override
    public BasicEvaluation delete(BasicEvaluation entity) {
        return null;
    }
}
