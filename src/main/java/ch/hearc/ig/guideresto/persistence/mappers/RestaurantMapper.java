package ch.hearc.ig.guideresto.persistence.mappers;

import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantType;
import ch.hearc.ig.guideresto.persistence.cache.Cache;
import ch.hearc.ig.guideresto.persistence.database.DatabaseProvider;
import ch.hearc.ig.guideresto.persistence.database.exceptions.DatabaseMapperException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;
import java.util.Set;

public class RestaurantMapper extends AbstractMapper<Restaurant> {
    @Override
    protected Restaurant mapToObj(ResultSet rs) throws SQLException {
        var fkCity = rs.getInt("FK_VILL");
        var fkType = rs.getInt("FK_TYPE");
        var restaurant = new Restaurant(
                rs.getInt("NUMERO"),
                rs.getString("NOM"),
                rs.getString("DESCRIPTION"),
                rs.getString("SITE_WEB"),
                rs.getString("ADRESSE"),
                // Eager load relations, without inner join -> this will cause N+1 problem to appear
                // I try to mitigate this problem introducing cache in relations via the cache class
                Cache.getCacheInstance(City.class).getFirstOr(
                        c -> c.getId() == fkCity,
                        () -> getToOneRelation(new CityMapper(), fkCity)
                ),
                Cache.getCacheInstance(RestaurantType.class).getFirstOr(
                        r -> r.getId() == fkType,
                        () -> getToOneRelation(new RestaurantTypeMapper(), fkType)
                )
        );
        // get all evaluations from custom mapper
        restaurant.getEvaluations().addAll(
                new EvaluationGenericMapper()
                        .getAllEvaluationsByRestaurant(rs.getInt("NUMERO")
                        )
        );
        return restaurant;
    }

    @Override
    public Optional<Restaurant> find(int id) throws DatabaseMapperException {
        try (var query = DatabaseProvider
                .preparedQueryOf(
                        "select NUMERO, NOM, DESCRIPTION, SITE_WEB, ADRESSE, FK_VILL, FK_TYPE from RESTAURANTS where NUMERO = ?")
        ) {
            return map(query.bind(id).execute());
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query find", e);
        }
    }

    @Override
    public Set<Restaurant> getAll() {
        try (var query = DatabaseProvider
                .preparedQueryOf(
                        "select NUMERO, NOM, DESCRIPTION, SITE_WEB, ADRESSE, FK_VILL, FK_TYPE from RESTAURANTS")
        ) {
            return mapAll(query.execute());
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query findAll", e);
        }
    }

    @Override
    public Set<Restaurant> getWhere(String column, String value) {
        try (var query = DatabaseProvider
                .preparedQueryOf(
                        "select NUMERO, NOM, DESCRIPTION, SITE_WEB, ADRESSE, FK_VILL, FK_TYPE from RESTAURANTS where ? = ?")
        ) {
            return mapAll(query.bind(column).bind(value).execute());
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query getWhere", e);
        }
    }

    public Set<Restaurant> searchByName(String value) {
        try (var query = DatabaseProvider
                .preparedQueryOf(
                        "select NUMERO, NOM, DESCRIPTION, SITE_WEB, ADRESSE, FK_VILL, FK_TYPE from RESTAURANTS where upper(NOM) like upper('%' || ? || '%')")
        ) {
            return mapAll(query.bind(value).execute());
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query searchByName", e);
        }
    }

    @Override
    public Restaurant insert(Restaurant entity) {
        // Before inserting restaurant, need to check city and type existence or insert
        var city = Cache.getCacheInstance(City.class)
                        .update(new CityMapper().insertIfNotExists(entity.getCity()));
        var restaurantType = Cache.getCacheInstance(RestaurantType.class)
                                  .update(new RestaurantTypeMapper().insertIfNotExists(entity.getType()));
        // FIX : Ideally we need to check if the entity already have an id to protect from double inserts
        try (var query = DatabaseProvider
                .preparedQueryOf(
                        "insert into RESTAURANTS (NOM, ADRESSE, DESCRIPTION, SITE_WEB, FK_TYPE, FK_VILL) VALUES (?, ?, ?, ?, ?, ?) returning NUMERO into ?")
        ) {
            query
                    .bind(entity.getName())
                    .bind(entity.getAddress().getStreet())
                    .bind(entity.getDescription())
                    .bind(entity.getWebsite())
                    .bind(restaurantType.getId())
                    .bind(city.getId())
                    .registerReturnParameter(
                            Types.INTEGER) // register a return parameter for the use of the returning keyword in sql query
                    .executeUpdate();
            var insertedId = query.getReturnedInt().orElseThrow(); // If no id returned, should throw
            entity.setId(insertedId);
            return entity;
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query insert", e);
        }
    }

    @Override
    public Restaurant update(Restaurant entity) {
        return null;
    }

    @Override
    public Restaurant delete(Restaurant entity) {
        try (var query = DatabaseProvider
                .preparedQueryOf(
                        "delete from RESTAURANTS where NUMERO = ?")
        ) {
            query
                    .bind(entity.getId())
                    .executeUpdate();
            return entity; // the deleted entity
        } catch (Exception e) {
            throw new DatabaseMapperException("Error while executing mapper query insert", e);
        }
    }
}
