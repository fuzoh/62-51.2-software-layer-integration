package ch.hearc.ig.guideresto.persistence.services;

import ch.hearc.ig.guideresto.business.EvaluationCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.Set;
import java.util.stream.Collectors;

public class EvaluationCriteriaService extends Service {

    public EvaluationCriteriaService(EntityManagerFactory emf) {
        super(emf);
    }

    public Set<EvaluationCriteria> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<EvaluationCriteria> query = em.createQuery(
                "SELECT e FROM EvaluationCriteria e", EvaluationCriteria.class);
        return query.getResultStream().collect(Collectors.toSet());
    }

}
