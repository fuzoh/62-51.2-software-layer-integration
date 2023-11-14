package ch.hearc.ig.guideresto.persistence.services;

import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.persistence.mappers.RestaurantMapper;

import java.util.Set;

public class RestaurantService {

    private final RestaurantMapper restaurantMapper;

    public RestaurantService(RestaurantMapper restaurantMapper) {
        this.restaurantMapper = restaurantMapper;
    }


    public Set<Restaurant> getAll() {
        return restaurantMapper.getAll();
    }
}
