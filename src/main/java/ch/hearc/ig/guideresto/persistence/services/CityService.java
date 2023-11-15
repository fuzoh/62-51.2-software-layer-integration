package ch.hearc.ig.guideresto.persistence.services;

import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.persistence.cache.Cache;
import ch.hearc.ig.guideresto.persistence.database.DatabaseProvider;
import ch.hearc.ig.guideresto.persistence.mappers.CityMapper;

import java.util.Set;

public class CityService {

    private final CityMapper cityMapper;

    private final Cache<City> cache;

    public CityService(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
        this.cache = Cache.getCacheInstance(City.class);
    }

    public void insert(City city) {
        cache.add(cityMapper.insert(city));
        DatabaseProvider.commit();
    }

    public Set<City> getAll() {
        if (cache.isEmpty()) {
            cache.populate(cityMapper.getAll());
        }
        return cache.getAll();
    }
}
