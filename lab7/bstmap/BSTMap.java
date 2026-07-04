package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public void setLeft(BSTNode left) {
            this.left = left;
        }

        public void setRight(BSTNode right) {
            this.right = right;
        }
    }

    private BSTNode root;
    private int size;

    public BSTMap() {
        this.size = 0;
        this.root = null;
    }

    public void clear() {
        this.size = 0;
        this.root = null;
    }

    public boolean containsKey(K key) {
        if (this.root == null) {
            return false;
        }
        BSTNode node = this.root;
        while (node != null) {
            if (node.key.compareTo(key) == 0) {
                return true;
            } else if (node.key.compareTo(key) > 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return false;
    }

    public V get(K key) {
        if (this.root == null) {
            return null;
        }
        BSTNode node = this.root;
        while (node != null) {
            if (node.key.compareTo(key) == 0) {
                return node.value;
            } else if (node.key.compareTo(key) > 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    public int size() {
        return this.size;
    }

    public void put(K key, V value) {
        if (this.root == null) {
            this.root = new BSTNode(key, value);
            size += 1;
            return;
        }

        putValue(this.root, key, value);
        size += 1;
    }

    private void putValue(BSTNode node, K key, V value) {
        if (node.key.compareTo(key) == 0) {
            return;
        } else if (node.key.compareTo(key) > 0) {
            if (node.left != null) {
                putValue(node.left, key, value);
                return;
            }
            node.left = new BSTNode(key, value);
        } else {
            if (node.right != null) {
                putValue(node.right, key, value);
                return;
            }
            node.right = new BSTNode(key, value);
        }
    }

    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        addKeys(set, this.root);
        return set;
    }

    private void addKeys(Set<K> set, BSTNode node) {
        if (node == null) {
            return;
        }
        set.add(node.key);
        addKeys(set, node.left);
        addKeys(set, node.right);
    }

    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        V res = get(key);
        this.root = deleteNode(root, key);
        size -= 1;
        return res;
    }

    private BSTNode deleteNode(BSTNode node, K key) {
        if (node == null) {
            return null;
        }

        if (node.key.compareTo(key) > 0) {
            node.left = deleteNode(node.left, key);
            return node;
        } else if (node.key.compareTo(key) < 0) {
            node.right = deleteNode(node.right, key);
            return node;
        }

        if (node.left == null && node.right == null) {
            return null;
        } else if (node.left != null && node.right == null) {
            return node.left;
        } else if (node.right != null && node.left == null) {
            return node.right;
        }

        BSTNode newNode = node.right;
        newNode.right = deleteNode(newNode, newNode.key);
        newNode.left = node.left;
        return newNode;

    }

    public V remove(K key, V value) {
        if (!containsKey(key) || get(key) != value) {
            return null;
        }
        return remove(key);
    }

    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        printNode(root);
    }

    private void printNode(BSTNode node) {
        if (node == null) {
            return;
        }
        printNode(node.left);
        System.out.print(node.key);
        printNode(node.right);
    }
}
