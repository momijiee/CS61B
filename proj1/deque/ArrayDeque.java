package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int first;
    private int last;


    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    public ArrayDeque(T item) {
        items = (T[]) new Object[8];
        items[0] = item;
        size = 1;
        first = 0;
        last = 0;
    }

    private int prevIndex(int index, int length) {
        index -= length;
        if (index < 0) {
            index += items.length;
        }
        return index;
    }

    private int nextIndex(int index, int length) {
        index += length;
        if (index >= items.length) {
            index -= items.length;
        }
        return index;
    }

    public void addFirst(T item) {
        first = prevIndex(this.first, 1);
        items[first] = item;
        size += 1;
    }

    public void addLast(T item) {
        last = nextIndex(this.last, 1);
        items[last] = item;
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
        T res = items[first];
        items[first] = null;
        first = nextIndex(first, 1);
        return res;
    }

    public T removeLast() {
        T res = items[last];
        items[last] = null;
        last = prevIndex(last, 1);
        return res;
    }

    public T get(int index) {
        int i = nextIndex(first, index);
        return items[i];
    }
}
