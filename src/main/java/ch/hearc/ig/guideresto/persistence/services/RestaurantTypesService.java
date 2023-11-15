package ch.hearc.ig.guideresto.persistence.services;

import ch.hearc.ig.guideresto.business.RestaurantType;
import ch.hearc.ig.guideresto.persistence.cache.Cache;
import ch.hearc.ig.guideresto.persistence.mappers.RestaurantTypeMapper;

import java.util.Set;

public class RestaurantTypesService {

    private final RestaurantTypeMapper restaurantTypeMapper;

    private final Cache<RestaurantType> cache;

    public RestaurantTypesService(RestaurantTypeMapper restaurantTypeMapper) {
        this.restaurantTypeMapper = restaurantTypeMapper;
        this.cache = Cache.getCacheInstance(RestaurantType.class);
    }

    public Set<RestaurantType> getAll() {
        if (cache.isEmpty()) {
            cache.populate(restaurantTypeMapper.getAll());
        }
        return cache.getAll();
    }
}
