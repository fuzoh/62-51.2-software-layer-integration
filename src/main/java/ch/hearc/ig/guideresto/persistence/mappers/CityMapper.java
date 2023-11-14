package ch.hearc.ig.guideresto.persistence.mappers;

import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.persistence.database.DatabaseProvider;
import ch.hearc.ig.guideresto.persistence.database.exceptions.DatabaseMapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public class CityMapper extends AbstractMapper<City> {

    protected City mapToObj(ResultSet rs) throws SQLException {
        return new City(
                rs.getInt("NUMERO"),
                rs.getString("CODE_POSTAL"),
                rs.getString("NOM_VILLE")
        );
    }

    @Override
    public Optional<City> find(int id) throws DatabaseMapperException {
        try (var query = DatabaseProvider
                .preparedQueryOf("select NUMERO, CODE_POSTAL, NOM_VILLE from VILLES where NUMERO = ?")
        ) {
            return map(query.bind(id).execute());
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query find", e);
        }
    }

    @Override
    public Set<City> getAll() {
        try (var query = DatabaseProvider
                .preparedQueryOf("select NUMERO, CODE_POSTAL, NOM_VILLE from VILLES")
        ) {
            return mapAll(query.execute());
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query findAll", e);
        }
    }

    @Override
    public Set<City> getWhere(String column, String value) {
        try (var query = DatabaseProvider
                .preparedQueryOf("select NUMERO, CODE_POSTAL, NOM_VILLE from VILLES where ? = ?")
        ) {
            return mapAll(query.bind(column).bind(value).execute());
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query findAll", e);
        }
    }

    @Override
    public City insert(City entity) {
        try (var query = DatabaseProvider
                .preparedQueryOf("insert into VILLES (CODE_POSTAL, NOM_VILLE) VALUES (?, ?)")
        ) {
            query.bind(entity.getZipCode()).bind(entity.getCityName()).execute();
            return entity;
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query insert", e);
        }
    }

    @Override
    public City update(City entity) {
        return null;
    }

    @Override
    public City delete(City entity) {
        return null;
    }
}
