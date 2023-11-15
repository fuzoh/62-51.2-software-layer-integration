package ch.hearc.ig.guideresto.persistence.mappers;

import ch.hearc.ig.guideresto.business.EvaluationCriteria;
import ch.hearc.ig.guideresto.persistence.database.DatabaseProvider;
import ch.hearc.ig.guideresto.persistence.database.exceptions.DatabaseMapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public class EvaluationCriteriaMapper extends AbstractMapper<EvaluationCriteria> {
    @Override
    protected EvaluationCriteria mapToObj(ResultSet rs) throws SQLException {
        return new EvaluationCriteria(
                rs.getInt("NUMERO"),
                rs.getString("NOM"),
                rs.getString("DESCRIPTION")
        );
    }

    @Override
    public Optional<EvaluationCriteria> find(int id) throws DatabaseMapperException {
        try (var query = DatabaseProvider
                .preparedQueryOf(
                        "select NUMERO, NOM, DESCRIPTION from CRITERES_EVALUATION where NUMERO = ?")
        ) {
            return map(query.bind(id).execute());
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query find", e);
        }
    }

    @Override
    public Set<EvaluationCriteria> getAll() {
        try (var query = DatabaseProvider
                .preparedQueryOf(
                        "select NUMERO, NOM, DESCRIPTION from CRITERES_EVALUATION")
        ) {
            return mapAll(query.execute());
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query find", e);
        }
    }

    @Override
    public Set<EvaluationCriteria> getWhere(String column, String value) {
        return null;
    }

    @Override
    public EvaluationCriteria insert(EvaluationCriteria entity) {
        return null;
    }

    @Override
    public EvaluationCriteria update(EvaluationCriteria entity) {
        return null;
    }

    @Override
    public EvaluationCriteria delete(EvaluationCriteria entity) {
        return null;
    }
}
