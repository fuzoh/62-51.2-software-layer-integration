package ch.hearc.ig.guideresto.persistence.services;

import ch.hearc.ig.guideresto.business.EvaluationCriteria;
import ch.hearc.ig.guideresto.persistence.cache.Cache;
import ch.hearc.ig.guideresto.persistence.mappers.EvaluationCriteriaMapper;

import java.util.Set;

public class EvaluationCriteriaService {

    private final EvaluationCriteriaMapper evaluationCriteriaMapper;
    private final Cache<EvaluationCriteria> cache;

    public EvaluationCriteriaService(EvaluationCriteriaMapper evaluationCriteriaMapper) {
        this.evaluationCriteriaMapper = evaluationCriteriaMapper;
        this.cache = Cache.getCacheInstance(EvaluationCriteria.class);
    }

    public Set<EvaluationCriteria> getAll() {
        // Check if elements are in the cache
        // Probably not a good thing, because it not guaranties that the cache is up-to-date with db
        if (cache.isEmpty()) {
            cache.populate(evaluationCriteriaMapper.getAll());
        }
        return cache.getAll();
    }
}
