package fr.eni.clinique.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionUtil {

    /**
     * // Predicate Function. This is Java 7 Method. For Jav , use streams and filter function.
     * @author DEFORTESCU
     *
     * @param <T>
     */
    public interface Predicate<T> {
        boolean apply(T type);
    }

    /**
     * Filter in Collection.
     * This is Java 7 Method. For Jav , use streams and filter function.
     * 
     * @param collection The Collection.
     * @param predicate The Predicate
     * 
     * @return Filtered Collection in a List.
     */
    public static <T> List<T> filter(Collection<T> collection, Predicate<T> predicate) {

        List<T> result = new ArrayList<T>();

        for (T element : collection) {
            if (predicate.apply(element)) {
                result.add(element);
            }
        }

        return result;
    }
}
