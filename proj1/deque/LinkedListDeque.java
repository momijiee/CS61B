package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T>{
    public class ListNode {
        public T item;
        public ListNode prev;
        public ListNode next;

        public ListNode() {

        }

        public ListNode(T item) {
            this.item = item;
        }
    }

    private int size;
    private final ListNode sentinel;

    public LinkedListDeque(){
        sentinel = new ListNode();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item){
        sentinel = new ListNode();
        ListNode node = new ListNode(item);
        sentinel.next = node;
        sentinel.prev = node;
        node.next = sentinel;
        node.prev = sentinel;
        size = 1;
    }

    public void addFirst(T item) {
        ListNode node = new ListNode(item);
        node.next = sentinel.next;
        node.next.prev = node;
        node.prev = sentinel;
        sentinel.next = node;
        size += 1;
    }

    public void addLast(T item) {
        ListNode node = new ListNode(item);
        node.prev = sentinel.prev;
        node.prev.next = node;
        node.next = sentinel;
        sentinel.prev = node;
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        ListNode p = sentinel.next;
        if (p == sentinel) {
            System.out.println("empty deque");
        }
        while (p.next != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println(p.item);
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        ListNode removedNode = sentinel.next;
        sentinel.next = removedNode.next;
        removedNode.next.prev = sentinel;
        size -= 1;
        return removedNode.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        ListNode removedNode = sentinel.prev;
        sentinel.prev =removedNode.prev;
        removedNode.prev.next = sentinel;
        size -= 1;
        return removedNode.item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        ListNode p = sentinel;
        for (int i = 0; i < index + 1; i++) {
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    /** Helper function for getRecursive function. */
    private  T getRecursiveHelper(ListNode p, int index) {
        if (index == 0) {
            return p.item;
        }
        else {
            return getRecursiveHelper(p.next, index - 1);
        }
    }

    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<T>{
        private ListNode p;
        public DequeIterator() {
            p = sentinel;
        }
        public boolean hasNext() {
            return p.next != sentinel;
        }
        public T next() {
            p = p.next;
            T res = p.item;
            return res;
        }
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }

        Deque<T> other = (Deque<T>) o;

        if (other.size() != this.size()) {
            return false;
        }

        for (int i=0; i<this.size; i++) {
            if (!(this.get(i).equals(other.get(i)))) {
                return false;
            }
        }

        return true;
    }

}
