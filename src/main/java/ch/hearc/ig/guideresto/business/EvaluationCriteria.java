package ch.hearc.ig.guideresto.business;

import ch.hearc.ig.guideresto.persistence.cache.CacheAble;

import java.util.Objects;

public class EvaluationCriteria implements CacheAble {

    private Integer id;
    private String name;
    private String description;

    public EvaluationCriteria(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvaluationCriteria that = (EvaluationCriteria) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}