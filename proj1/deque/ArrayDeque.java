package deque;

public class ArrayDeque<T> {
    private final T[] items;
    private int size; // the number of elements in items.

    /** The start point of the deque, the first element comes after prev(might be null). */
    private int prev;


    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;

        /* the element before items[0]. */
        prev = getIndex(0, -1);
    }

    public ArrayDeque(T item) {
        items = (T[]) new Object[8];
        items[0] = item;
        size = 1;

        /* the element before items[0]. */
        prev = getIndex(0, -1);
    }

    /** Get a valid index from 0 to items.length - 1,
     * starting at POS, moving diff positions. */
    private int getIndex(int pos, int diff) {
        int res = (pos + diff) % items.length;
        return res < 0 ? res + items.length : res;
    }

    public void addFirst(T item) {
        items[prev] = item;
        prev = getIndex(prev, -1);
        size += 1;
    }

    public void addLast(T item) {
        items[getIndex(prev, size + 1)] = item;
        size += 1;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {

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
        return res;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int last = getIndex(prev, size + 1);
        T res = items[last];
        items[last] = null;
        size -= 1;
        return res;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int i = getIndex(prev, index + 1);
        return items[i];
    }
}
