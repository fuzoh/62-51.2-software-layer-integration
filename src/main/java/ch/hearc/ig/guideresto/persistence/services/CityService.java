package ch.hearc.ig.guideresto.persistence.services;

import ch.hearc.ig.guideresto.business.City;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.Set;
import java.util.stream.Collectors;

public class CityService extends Service {

    public CityService(EntityManagerFactory emf) {
        super(emf);
    }

    public void insert(City city) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(city);
            em.getTransaction().commit();
        }
    }

    public Set<City> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<City> query = em.createQuery("SELECT c FROM City c", City.class);
            return query.getResultStream().collect(Collectors.toSet());
        }
    }

}
