package ch.hearc.ig.guideresto.persistence.cache;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Cache<T> {

    Set<T> data;

    public Cache() {
        this.data = new HashSet<>();
    }

    public Cache<T> populate(Set<T> newData) {
        data.addAll(newData);
        return this;
    }

    public Optional<T> getFirst(Predicate<T> predicate) {
        return data.stream().filter(predicate).findFirst();
    }

    public Set<T> getMatches(Predicate<T> predicate) {
        return data.stream().filter(predicate).collect(Collectors.toSet());
    }

    // To check if the cache already contain items
    // This is absolutely not a good approach, because we don't check that the cache is coherent with the database.
    public boolean isPopulated() {
        return !data.isEmpty();
    }
    public boolean isEmpty() {
        return data.isEmpty();
    }

    public Set<T> getAll() {
        return data;
    }
}
