package ch.hearc.ig.guideresto.persistence.services;

import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.persistence.cache.Cache;
import ch.hearc.ig.guideresto.persistence.mappers.RestaurantMapper;

import java.util.Set;

public class RestaurantService {

    private final RestaurantMapper restaurantMapper;

    private final Cache<Restaurant> cache;

    public RestaurantService(RestaurantMapper restaurantMapper) {
        this.restaurantMapper = restaurantMapper;
        this.cache = Cache.getCacheInstance(Restaurant.class);
    }

    public Set<Restaurant> getAll() {
        // Check if elements are in the cache
        // Probably not a good thing, because it not guaranties that the cache is up-to-date with db
        if (cache.isEmpty()) {
            cache.populate(restaurantMapper.getAll());
        }
        return cache.getAll();
    }

    public Set<Restaurant> searchByName(String needle) {
        if (cache.isEmpty()) {
            cache.populate(restaurantMapper.getAll());
        }
        return cache.getMatches(r -> r.getName().equalsIgnoreCase(needle));
    }

    public Set<Restaurant> searchByCityName(String needle) {
        if (cache.isEmpty()) {
            cache.populate(restaurantMapper.getAll());
        }
        return cache.getMatches(r -> r.getAddress().getCity().getCityName().toUpperCase().contains(needle.toUpperCase()));
    }

    public void add(Restaurant restaurant) {
        // TODO : transaction and add restaurant to database !!!
    }
}
