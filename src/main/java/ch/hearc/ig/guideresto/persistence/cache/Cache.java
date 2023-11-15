package ch.hearc.ig.guideresto.persistence.cache;

import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This class is a generic cache implementation, it allows to store data in memory
 * it also implements a static singleton like pattern to ensure that only one cache instance by type is created
 * @param <T> The type of the data class that the cache instance will cache
 */
public class Cache<T> {

    /**
     * Static store for all cache instances
     */
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
        return (Cache<E>) caches.get(type); // We need to cast to get autocomplete, this is "safe" because the type is checked with the generic
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

    /**
     * This method allows to test if a given element is present in cache, if not,
     * you provide a supplier that will populate the cache
     */
    public T getFirstOr(Predicate<T> predicate, Supplier<T> supplier) {
        var element = getFirst(predicate);
        if (element.isEmpty()) {
            element = Optional.of(supplier.get());
            update(element.get());
        }
        return element.get();
    }

    public Set<T> getMatches(Predicate<T> predicate) {
        return data.stream().filter(predicate).collect(Collectors.toSet());
    }

    /**
     * This method allows to test if a given suite element is present in cache, if not,
     * you provide a supplier that will populate the cache
     */
    public Set<T> getMatchesOr(Predicate<T> predicate, Supplier<Set<T>> supplier) {
        var elements = getMatches(predicate);
        if (elements.isEmpty()) {
            elements.addAll(supplier.get());
        }
        return elements;
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
