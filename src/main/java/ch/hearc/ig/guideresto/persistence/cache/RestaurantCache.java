package ch.hearc.ig.guideresto.persistence.cache;

import ch.hearc.ig.guideresto.business.Restaurant;

import java.util.Set;

public class RestaurantCache {

    private Set<Restaurant> data;

    public RestaurantCache(Set<Restaurant> cacheInitialData) {
        this.data = cacheInitialData;
    }

    public RestaurantCache() {
    }
}
