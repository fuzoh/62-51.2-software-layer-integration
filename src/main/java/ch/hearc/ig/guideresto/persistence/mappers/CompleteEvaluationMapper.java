package ch.hearc.ig.guideresto.persistence.mappers;

import ch.hearc.ig.guideresto.business.CompleteEvaluation;
import ch.hearc.ig.guideresto.persistence.database.DatabaseProvider;
import ch.hearc.ig.guideresto.persistence.database.exceptions.DatabaseMapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public class CompleteEvaluationMapper extends AbstractMapper<CompleteEvaluation> {
    @Override
    protected CompleteEvaluation mapToObj(ResultSet rs) throws SQLException {
        var id = rs.getInt("NUMERO");
        var e = new CompleteEvaluation(
                id,
                rs.getDate("DATE_EVAL").toLocalDate(),
                null,
                rs.getString("COMMENTAIRE"),
                rs.getString("NOM_UTILISATEUR")
        );
        e.getGrades().addAll(getToManyRelation(new GradeMapper(), "FK_COMM", Integer.toString(id)));
        return e;
    }

    @Override
    public Optional<CompleteEvaluation> find(int id) throws DatabaseMapperException {
        return Optional.empty();
    }

    @Override
    public Set<CompleteEvaluation> getAll() {
        return null;
    }

    @Override
    public Set<CompleteEvaluation> getWhere(String column, String value) {
        try (var query = DatabaseProvider
                .preparedQueryOf(
                        "select NUMERO, DATE_EVAL, COMMENTAIRE, NOM_UTILISATEUR, FK_REST from COMMENTAIRE where ? = ?")
        ) {
            return mapAll(query.bind(column).bind(value).execute());
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query findAll", e);
        }
    }

    @Override
    public CompleteEvaluation insert(CompleteEvaluation entity) {
        return null;
    }

    @Override
    public CompleteEvaluation update(CompleteEvaluation entity) {
        return null;
    }

    @Override
    public CompleteEvaluation delete(CompleteEvaluation entity) {
        return null;
    }
}
