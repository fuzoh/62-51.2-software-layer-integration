package ch.hearc.ig.guideresto.persistence.cache;

import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Cache<T> {

    // Store all actives caches by entity type
    static Map<Class<? extends CacheAble>, Cache<? extends CacheAble>> caches = new HashMap<>();

    /**
     * This is a kind of singleton for cache instances
     * For each cache type a store a new key in a hashmap
     * Every time a cache is requested for a given data type
     * A check is made in the caches store and return it or create it
     */
    public static <E extends CacheAble> Cache<E> getCacheInstance(Class<E> type) {
        if (!caches.containsKey(type)) {
            caches.put(type, new Cache<E>());
        }
        return (Cache<E>) caches.get(type);
    }

    Set<T> data;

    // Private constructor to prevent creation form outside
    private Cache() {
        this.data = new HashSet<>();
    }

    public Cache<T> populate(Set<T> newData) {
        data.addAll(newData);
        return this;
    }

    public T update(T element) {
        if (!data.add(element)) {
            data.remove(element);
            data.add(element);
        }
        return element;
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

    public void remove(T element) {
        data.remove(element);
    }
}
