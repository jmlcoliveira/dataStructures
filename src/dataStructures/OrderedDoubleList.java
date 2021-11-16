package dataStructures;

import dataStructures.exceptions.EmptyDictionaryException;

import java.io.Serializable;

public class OrderedDoubleList<K extends Comparable<K>, V> implements OrderedDictionary<K, V> {

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
        if(isEmpty()) return null;
        if(key.compareTo(head.getKey()) == 0) return head;
        if(key.compareTo(tail.getKey()) == 0) return tail;
        for (SingleEntry<K, V> node = head.getNext(); node != null && node.getKey().compareTo(key) <= 0; node = node.next) //only search if the key of current node is <= than target key
            if (node.getKey().equals(key))                                                                      // because list is ordered
                return node;
        return null;
    }

    @Override
    public V insert(K key, V value) {
        SingleEntry<K, V> node = findNode(key);
        if (node != null) { //replace old value and return the old value
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
        // if key is lower than head, insert before head
        if (key.compareTo(head.getKey()) < 0) {
            insertFirst(node);
            return null;
        }
        // if key is higher than tail, insert after tail
        if (key.compareTo(tail.getKey()) > 0) {
            insertLast(node);
            return null;
        }

        insertMiddle(node);
        return null;
    }

    //Pre-Condition:node is not in the first position nor the last position
    protected void insertMiddle(SingleEntry<K, V> node) {
        //find pos to insert
        //nodes are ordered by ascending order of keys
        for (SingleEntry<K, V> currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
            //insert before the first element which is greater than key
            if (currentNode.getKey().compareTo(node.getKey()) > 0) {
                SingleEntry<K, V> prevNode = currentNode.getPrevious();
                prevNode.setNext(node);
                node.setPrevious(prevNode);
                node.setNext(currentNode);
                currentNode.setPrevious(node);
                currentSize++;
                break;
            }
        }
    }

    //Pre-condition: tail != null
    protected void insertLast(SingleEntry<K, V> node) {
        tail.setNext(node);
        node.setPrevious(tail);
        tail = node;
        currentSize++;
    }

    //Pre-condition: head != null
    protected void insertFirst(SingleEntry<K, V> node) {
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

    @Override
    public Entry<K, V> minEntry() throws EmptyDictionaryException {
        if (isEmpty()) throw new EmptyDictionaryException();
        return head;
    }

    @Override
    public Entry<K, V> maxEntry() throws EmptyDictionaryException {
        if (isEmpty()) throw new EmptyDictionaryException();
        return tail;
    }

    class EntryIterator implements Iterator<Entry<K, V>>, Serializable {

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
