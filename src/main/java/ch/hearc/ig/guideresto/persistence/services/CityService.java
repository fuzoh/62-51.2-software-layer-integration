package ch.hearc.ig.guideresto.persistence.services;

import ch.hearc.ig.guideresto.business.City;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CityService {

    EntityManagerFactory emf;

    public CityService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void insert(City city) {
        //
    }

    public Set<City> getAll() {
        var em = emf.createEntityManager();
        var query = em.createQuery("SELECT c FROM City c", City.class);
        return query.getResultStream().collect(Collectors.toSet());
    }
}
