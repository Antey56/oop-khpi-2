package ua.khpi.oop.lab10;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ReservationContainer<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_FACTOR = 2;

    private T[] items;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public ReservationContainer() {
        this.capacity = DEFAULT_CAPACITY;
        this.items = (T[]) new Object[capacity];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public ReservationContainer(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }

        this.capacity = initialCapacity;
        this.items = (T[]) new Object[capacity];
        this.size = 0;
    }

    public void add(T item) {
        if (size == capacity) {
            grow();
        }

        items[size++] = item;
    }

    public T get(int index) {
        checkIndex(index);
        return items[index];
    }

    public T set(int index, T item) {
        checkIndex(index);
        T oldItem = items[index];
        items[index] = item;
        return oldItem;
    }

    public T remove(int index) {
        checkIndex(index);

        T removedItem = items[index];

        for (int i = index; i < size - 1; i++) {
            items[i] = items[i + 1];
        }

        items[--size] = null;

        return removedItem;
    }

    public boolean contains(T item) {
        return indexOf(item) >= 0;
    }

    public int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], item)) {
                return i;
            }
        }

        return -1;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        size = 0;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        capacity *= GROWTH_FACTOR;

        T[] newItems = (T[]) new Object[capacity];

        System.arraycopy(items, 0, newItems, 0, size);

        items = newItems;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ReservationContainerIterator();
    }

    private class ReservationContainerIterator implements Iterator<T> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in container");
            }

            return items[currentIndex++];
        }
    }
}
