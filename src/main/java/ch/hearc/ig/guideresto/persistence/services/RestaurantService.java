package ch.hearc.ig.guideresto.persistence.services;

import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class RestaurantService extends Service {

    public RestaurantService(EntityManagerFactory emf) {
        super(emf);
    }

    public Set<Restaurant> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Restaurant> query = em.createQuery(
                // Directly load type and city eagerly, these data will be needed in most cases
                "SELECT r FROM Restaurant r JOIN FETCH r.type t JOIN FETCH r.address.city c",
                Restaurant.class
        );
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
                // Fetch type eagerly (`FETCH` keyword)
                .createQuery("SELECT r FROM Restaurant r JOIN FETCH r.type t WHERE t = :type",
                             Restaurant.class
                ).setParameter("type", chosenType);
        return query.getResultStream().collect(Collectors.toSet());
    }

    public void remove(Restaurant restaurant) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(restaurant));
        em.getTransaction().commit();
    }

    public Set<Restaurant> searchByCityName(String needle) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Restaurant> query = em
                // Fetch city eagerly (`FETCH` keyword)
                .createQuery(
                        "SELECT r FROM Restaurant r JOIN FETCH r.address.city c WHERE c.cityName like :name",
                        Restaurant.class
                ).setParameter("name", "%" + needle + "%");
        return query.getResultStream().collect(Collectors.toSet());
    }

    public Restaurant loadEvaluations(Restaurant restaurant) {
        Objects.requireNonNull(restaurant.getId());
        EntityManager em = emf.createEntityManager();
        Restaurant managedRestaurant = em.merge(restaurant);
        // Load eagerly all properties of the restaurant
        Hibernate.initialize(managedRestaurant);
        return managedRestaurant;
    }

    public void update(Restaurant restaurant) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(restaurant);
        em.getTransaction().commit();
    }

}
