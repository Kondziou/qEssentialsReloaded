package me.kavzaq.qEssentialsReloaded.utils.reflections;

import org.apache.commons.lang.Validate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

/**
 * Represents a wrapper around an array class of an arbitrary reference type,
 * which properly implements "value" hash code and equality functions.
 * <p>
 * This class is intended for use as a key to a map.
 * </p>
 *
 * @param <E> The type of elements in the array.
 * @author Glen Husman
 * @see Arrays
 */
public final class ArrayWrapper<E> {

    @SafeVarargs
    public ArrayWrapper(E... elements) {
        setArray(elements);
    }

    private E[] _array;

    public E[] getArray() {
        return _array;
    }

    public void setArray(E[] array) {
        Validate.notNull(array, "array cannot be null");
        _array = array;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ArrayWrapper)) {
            return false;
        }
        return Arrays.equals(_array, ((ArrayWrapper) other)._array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(_array);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(Iterable<? extends T> list, Class<T> c) {
        int size = -1;
        if (list instanceof Collection<?>) {
            @SuppressWarnings("rawtypes")
            Collection coll = (Collection) list;
            size = coll.size();
        }


        if (size < 0) {
            size = 0;
            for (@SuppressWarnings("unused") T element : list) {
                size++;
            }
        }

        T[] result = (T[]) Array.newInstance(c, size);
        int i = 0;
        for (T element : list) {
            result[i++] = element;
        }
        return result;
    }
}
