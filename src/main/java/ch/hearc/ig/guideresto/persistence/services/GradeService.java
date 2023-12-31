package ch.hearc.ig.guideresto.persistence.services;

import ch.hearc.ig.guideresto.business.Grade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class GradeService extends Service {

    public GradeService(EntityManagerFactory emf) {
        super(emf);
    }

    public void add(Grade grade) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(grade);
        em.getTransaction().commit();
    }

}
