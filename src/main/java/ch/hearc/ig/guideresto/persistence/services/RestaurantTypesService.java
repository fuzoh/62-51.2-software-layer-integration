package ch.hearc.ig.guideresto.persistence.services;

import ch.hearc.ig.guideresto.business.RestaurantType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.Set;
import java.util.stream.Collectors;

public class RestaurantTypesService extends Service {

    public RestaurantTypesService(EntityManagerFactory emf) {
        super(emf);
    }

    public Set<RestaurantType> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<RestaurantType> query = em.createQuery(
                "SELECT r FROM RestaurantType r", RestaurantType.class);
        return query.getResultStream().collect(Collectors.toSet());
    }

}
