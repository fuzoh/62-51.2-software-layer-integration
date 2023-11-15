package ch.hearc.ig.guideresto.persistence.mappers;

import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.persistence.database.DatabaseProvider;
import ch.hearc.ig.guideresto.persistence.database.exceptions.DatabaseMapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public class RestaurantMapper extends AbstractMapper<Restaurant> {
    @Override
    protected Restaurant mapToObj(ResultSet rs) throws SQLException {
        return new Restaurant(
                rs.getInt("NUMERO"),
                rs.getString("NOM"),
                rs.getString("DESCRIPTION"),
                rs.getString("SITE_WEB"),
                rs.getString("ADRESSE"),
                // Eager load relations, without inner join -> this will cause N+1 problem to appear
                // TODO : mitigate this problem introducing cache in relations
                getToOneRelation(new CityMapper(), rs.getInt("FK_VILL")),
                getToOneRelation(new RestaurantTypeMapper(), rs.getInt("FK_TYPE"))
        );
    }

    @Override
    public Optional<Restaurant> find(int id) throws DatabaseMapperException {
        try (var query = DatabaseProvider
                .preparedQueryOf("select NUMERO, NOM, DESCRIPTION, SITE_WEB, ADRESSE, FK_VILL, FK_TYPE from RESTAURANTS where NUMERO = ?")
        ) {
            return map(query.bind(id).execute());
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query find", e);
        }
    }

    @Override
    public Set<Restaurant> getAll() {
        try (var query = DatabaseProvider
                .preparedQueryOf("select NUMERO, NOM, DESCRIPTION, SITE_WEB, ADRESSE, FK_VILL, FK_TYPE from RESTAURANTS")
        ) {
            var test = mapAll(query.execute());
            System.out.println(test.toString());
            return test;
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query findAll", e);
        }
    }

    @Override
    public Set<Restaurant> getWhere(String column, String value) {
        try (var query = DatabaseProvider
                .preparedQueryOf("select NUMERO, NOM, DESCRIPTION, SITE_WEB, ADRESSE, FK_VILL, FK_TYPE from RESTAURANTS where ? = ?")
        ) {
            return mapAll(query.bind(column).bind(value).execute());
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query getWhere", e);
        }
    }

    public Set<Restaurant> searchByName(String value) {
        try (var query = DatabaseProvider
                .preparedQueryOf("select NUMERO, NOM, DESCRIPTION, SITE_WEB, ADRESSE, FK_VILL, FK_TYPE from RESTAURANTS where upper(NOM) like upper('%' || ? || '%')")
        ) {
            return mapAll(query.bind(value).execute());
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query searchByName", e);
        }
    }

    @Override
    public Restaurant insert(Restaurant entity) {
        return null;
    }

    @Override
    public Restaurant update(Restaurant entity) {
        return null;
    }

    @Override
    public Restaurant delete(Restaurant entity) {
        return null;
    }
}
