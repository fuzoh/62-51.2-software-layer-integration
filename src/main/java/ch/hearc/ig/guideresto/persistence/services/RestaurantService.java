package ch.hearc.ig.guideresto.persistence.services;

import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.Set;
import java.util.stream.Collectors;

public class RestaurantService extends Service {

    public RestaurantService(EntityManagerFactory emf) {
        super(emf);
    }

    public Set<Restaurant> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Restaurant> query = em.createQuery("SELECT r FROM Restaurant r", Restaurant.class);
        return query.getResultStream().collect(Collectors.toSet());
    }

    public Set<Restaurant> searchByName(String needle) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Restaurant> query = em
                .createQuery("SELECT r FROM Restaurant r WHERE name like :name", Restaurant.class)
                .setParameter("name", "%" + needle + "%");
        return query.getResultStream().collect(Collectors.toSet());
    }

    public void add(Restaurant restaurant) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(restaurant);
        em.getTransaction().commit();
    }

    public Set<Restaurant> getByType(RestaurantType chosenType) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Restaurant> query = em
                .createQuery("SELECT r FROM Restaurant r JOIN r.type t WHERE t = :type", Restaurant.class)
                .setParameter("type", chosenType);
        return query.getResultStream().collect(Collectors.toSet());
    }

    public void remove(Restaurant restaurant) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(restaurant));
        em.getTransaction().commit();
    }
}
