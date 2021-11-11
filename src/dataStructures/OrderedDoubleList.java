package dataStructures;

import java.io.Serializable;

public class OrderedDoubleList<K extends Comparable<K>, V> implements Dictionary<K, V> {

    static final long serialVersionUID = 0L;

    // Node at the head of the list.
    protected SingleEntry<K, V> head;
    // Node at the tail of the list.
    protected SingleEntry<K, V> tail;
    // Number of elements in the list.
    protected int currentSize;

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public V find(K key) {
        SingleEntry<K, V> nodeWithKey = findNode(key);
        return nodeWithKey == null ? null : nodeWithKey.getValue();
    }

    /**
     * Returns the node which contains the key or <code>null</code> if key does not exist
     *
     * @param key key
     * @return node containing the key or <code>null</code> if key does not exist
     */
    protected SingleEntry<K, V> findNode(K key) {
        for (SingleEntry<K, V> node = head; node != null && key.compareTo(node.getKey()) <= 0; node = node.next)
            if (node.getKey().equals(key))
                return node;
        return null;
    }

    @Override
    public V insert(K key, V value) {
        SingleEntry<K, V> node = findNode(key);
        if (node != null) {
            V oldValue = node.getValue();
            node.setValue(value);
            return oldValue;
        }

        node = new SingleEntry<>(value, key, null, null);
        if (isEmpty()) {
            head = node;
            tail = node;
            currentSize++;
            return null;
        }
        if (key.compareTo(head.getKey()) < 0) {
            insertFirst(node);
            return null;
        }
        if (key.compareTo(tail.getKey()) > 0) {
            insertLast(node);
            return null;
        }

        for (SingleEntry<K, V> currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
            if (key.compareTo(currentNode.getKey()) < 0) {
                SingleEntry<K, V> prevNode = currentNode.getPrevious();
                prevNode.setNext(node);
                node.setPrevious(prevNode);
                node.setNext(currentNode);
                currentNode.setPrevious(node);
                currentSize++;
                return null;
            }
        }

        return null;
    }

    protected void insertLast(SingleEntry<K, V> node) {
        tail.setNext(node);
        node.setPrevious(tail);
        tail = node;
        currentSize++;
    }

    protected void insertFirst(SingleEntry<K, V> node) {
        if (this.isEmpty())
            tail = node;
        else
            head.setPrevious(node);
        node.setNext(head);
        head = node;
        currentSize++;
    }

    @Override
    public V remove(K key) {
        SingleEntry<K, V> node = findNode(key);
        if (node == null) return null;

        if (node == head) {
            removeFirstNode();
        } else if (node == tail) {
            removeLastNode();
        } else
            removeMiddleNode(node);

        return node.getValue();
    }

    /**
     * Removes the first node in the list.
     * Pre-condition: the list is not empty.
     */
    protected void removeFirstNode() {
        if (currentSize == 1) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setPrevious(null);
        }
        currentSize--;
    }

    /**
     * Removes the last node in the list.
     * Pre-condition: the list is not empty.
     */
    protected void removeLastNode() {
        if (currentSize == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
        }
        currentSize--;
    }

    /**
     * Removes the specified node from the list.
     * Pre-condition: the node is neither the head nor the tail of the list.
     *
     * @param node - middle node to be removed
     */
    protected void removeMiddleNode(SingleEntry<K, V> node) {
        SingleEntry<K, V> prevNode = node.getPrevious();
        SingleEntry<K, V> nextNode = node.getNext();
        prevNode.setNext(nextNode);
        nextNode.setPrevious(prevNode);
        currentSize--;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new EntryIterator();
    }

    class EntryIterator implements Iterator<Entry<K, V>>, Serializable{

        static final long serialVersionUID = 0L;

        SingleEntry<K, V> next;
        SingleEntry<K, V> current;

        EntryIterator() {
            rewind();
        }

        public boolean hasNext() {
            return next != null;
        }

        public final Entry<K, V> next() {
            return nextNode();
        }

        protected Entry<K, V> nextNode() {
            current = next;
            next = next.getNext();
            return current;
        }

        public void rewind() {
            next = head;
            current = null;
        }
    }

    static class SingleEntry<K, V> implements Entry<K, V>, Serializable {

        static final long serialVersionUID = 0L;

        // Value stored in the node.
        private V value;

        //Key stored in the node
        private final K key;

        // (Pointer to) the previous node.
        private SingleEntry<K, V> previous;

        // (Pointer to) the next node.
        private SingleEntry<K, V> next;

        protected SingleEntry(V value, K key, SingleEntry<K, V> thePrevious,
                              SingleEntry<K, V> theNext) {
            this.value = value;
            this.key = key;
            previous = thePrevious;
            next = theNext;
        }

        protected SingleEntry(V value, K key) {
            this(value, key, null, null);
        }

        public V getValue() {
            return value;
        }

        public K getKey() {
            return key;
        }

        protected void setValue(V newValue) {
            value = newValue;
        }

        protected SingleEntry<K, V> getPrevious() {
            return previous;
        }

        protected void setPrevious(SingleEntry<K, V> newPrevious) {
            previous = newPrevious;
        }

        protected SingleEntry<K, V> getNext() {
            return next;
        }

        protected void setNext(SingleEntry<K, V> newNext) {
            next = newNext;
        }
    }
}
