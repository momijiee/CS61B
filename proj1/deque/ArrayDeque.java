package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size; // the number of elements in items.

    private static double RESIZE_LARGER = 0.75;
    private static double RESIZE_SMALLER = 0.25;
    private static int MIN_RESIZE_LENGTH = 16;

    /** The start point of the deque, the first element comes after prev(might be null). */
    private int prev;


    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;

        /* the element before items[0]. */
        prev = getIndex(0, -1);
    }

    /** Get a valid index from 0 to items.length - 1,
     * starting at POS, moving diff positions. */
    private int getIndex(int pos, int diff) {
        int res = (pos + diff) % items.length;
        return res < 0 ? res + items.length : res;
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newItems[i] = get(i);
        }
        items = newItems;
        prev = getIndex(0, -1);
    }

    public void addFirst(T item) {
        if (size > items.length * RESIZE_LARGER) {
            resize(items.length * 2);
        }
        items[prev] = item;
        prev = getIndex(prev, -1);
        size += 1;
    }

    public void addLast(T item) {
        if (size > items.length * RESIZE_LARGER) {
            resize(items.length * 2);
        }
        items[getIndex(prev, size + 1)] = item;
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (T item : this) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int first = getIndex(prev, 1);
        T res = items[first];
        items[first] = null;
        prev = first;
        size -= 1;
        if (items.length > MIN_RESIZE_LENGTH && size < items.length * RESIZE_SMALLER) {
            resize((int) Math.round(items.length * 0.5));
        }
        return res;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int last = getIndex(prev, size);
        T res = items[last];
        items[last] = null;
        size -= 1;
        if (items.length > MIN_RESIZE_LENGTH && size < items.length * RESIZE_SMALLER) {
            resize((int) Math.round(items.length * 0.5));
        }
        return res;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int i = getIndex(prev, index + 1);
        return items[i];
    }

    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<T> {
        private int p;
        public DequeIterator() {
            p = 0;
        }
        public boolean hasNext() {
            return p < size;
        }
        public T next() {
            T res = get(p);
            //T res = items[getIndex(prev, p + 1)];
            p += 1;
            return res;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;

        if (other.size() != this.size()) {
            return false;
        }

        for (int i = 0; i < this.size(); i++) {
            if (!(this.get(i).equals(other.get(i)))) {
                return false;
            }
        }

        return true;
    }
}
