package ch.hearc.ig.guideresto.persistence.mappers;

import ch.hearc.ig.guideresto.business.RestaurantType;
import ch.hearc.ig.guideresto.persistence.database.DatabaseProvider;
import ch.hearc.ig.guideresto.persistence.database.exceptions.DatabaseMapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public class RestaurantTypeMapper extends AbstractMapper<RestaurantType> {
    @Override
    protected RestaurantType mapToObj(ResultSet rs) throws SQLException {
        return new RestaurantType(
                rs.getInt("NUMERO"),
                rs.getString("LIBELLE"),
                rs.getString("DESCRIPTION")
        );
    }

    @Override
    public Optional<RestaurantType> find(int id) throws DatabaseMapperException {
        try (var query = DatabaseProvider
                .preparedQueryOf(
                        "select NUMERO, LIBELLE, DESCRIPTION from TYPES_GASTRONOMIQUES where NUMERO = ?")
        ) {
            return map(query.bind(id).execute());
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query find", e);
        }
    }

    @Override
    public Set<RestaurantType> getAll() {
        try (var query = DatabaseProvider
                .preparedQueryOf("select NUMERO, LIBELLE, DESCRIPTION from TYPES_GASTRONOMIQUES")
        ) {
            return mapAll(query.execute());
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query getAll", e);
        }
    }

    @Override
    public Set<RestaurantType> getWhere(String column, String value) {
        return null;
    }

    @Override
    public RestaurantType insert(RestaurantType entity) {
        return null;
    }

    @Override
    public RestaurantType update(RestaurantType entity) {
        return null;
    }

    @Override
    public RestaurantType delete(RestaurantType entity) {
        return null;
    }
}
