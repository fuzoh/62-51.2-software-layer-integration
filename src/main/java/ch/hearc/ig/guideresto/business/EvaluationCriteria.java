package ch.hearc.ig.guideresto.business;

import ch.hearc.ig.guideresto.persistence.cache.CacheAble;

public class EvaluationCriteria implements CacheAble {

    private Integer id;
    private String name;
    private String description;

    public EvaluationCriteria(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}