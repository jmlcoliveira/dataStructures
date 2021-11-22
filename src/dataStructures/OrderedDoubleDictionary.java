package dataStructures;

import dataStructures.exceptions.EmptyDictionaryException;

import java.io.Serializable;

public class OrderedDoubleDictionary<K extends Comparable<K>, V> implements OrderedDictionary<K, V> {

    static final long serialVersionUID = 0L;

    // Node at the head of the list.
    protected DoubleListNode<EntryClass<K, V>> head;
    // Node at the tail of the list.
    protected DoubleListNode<EntryClass<K, V>> tail;
    // Number of elements in the list.
    protected int currentSize;

    public OrderedDoubleDictionary() {
        head = null;
        tail = null;
        currentSize = 0;
    }

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
        DoubleListNode<EntryClass<K, V>> nodeWithKey = findNode(key);
        if (nodeWithKey == null)
            return null;
        return nodeWithKey.getElement().getValue();
    }

    /**
     * Returns the node which contains the key or <code>null</code> if key does not exist
     *
     * @param key key
     * @return node containing the key or <code>null</code> if key does not exist
     */
    protected DoubleListNode<EntryClass<K, V>> findNode(K key) {
        if (isEmpty()) return null;
        DoubleListNode<EntryClass<K, V>> node;
        for (node = head; node != null && node.getElement().getKey().compareTo(key) <= 0; node = node.getNext()) //only search if the key of current node is <= than target key
            if (node.getElement().getKey().equals(key))                                                                      // because list is ordered
                return node;
        return null;
    }

    @Override
    public V insert(K key, V value) {
        DoubleListNode<EntryClass<K, V>> node = null;
        DoubleListNode<EntryClass<K, V>> nodeAfter;
        for (nodeAfter = head; nodeAfter != null && nodeAfter.getElement().getKey().compareTo(key) <= 0; nodeAfter = nodeAfter.getNext()) {
            if (nodeAfter.getElement().getKey().equals(key)) {
                node = nodeAfter;
                break;
            }
        }
        EntryClass<K, V> entry;
        if (node == null)
            entry = null;
        else
            entry = node.getElement();
        if (entry != null) { //replace old value and return the old value
            V oldValue = entry.getValue();
            entry.setValue(value);
            return oldValue;
        }

        entry = new EntryClass<>(key, value);
        node = new DoubleListNode<>(entry);
        if (isEmpty()) {
            head = node;
            tail = node;
            currentSize++;
            return null;
        }
        // if key is lower than head, insert before head
        if (key.compareTo(head.getElement().getKey()) < 0) {
            insertFirst(node);
            return null;
        }
        // if key is higher than tail, insert after tail
        if (key.compareTo(tail.getElement().getKey()) > 0) {
            insertLast(node);
            return null;
        }

        assert nodeAfter != null;
        insertMiddle(node, nodeAfter);
        return null;
    }

    //Pre-Condition:node is not in the first position nor the last position
    protected void insertMiddle(DoubleListNode<EntryClass<K, V>> node, DoubleListNode<EntryClass<K, V>> nodeAfter) {
        DoubleListNode<EntryClass<K, V>> prevNode = nodeAfter.getPrevious();
        prevNode.setNext(node);
        node.setPrevious(prevNode);
        node.setNext(nodeAfter);
        nodeAfter.setPrevious(node);
        currentSize++;
    }

    //Pre-condition: tail != null
    protected void insertLast(DoubleListNode<EntryClass<K, V>> node) {
        tail.setNext(node);
        node.setPrevious(tail);
        tail = node;
        currentSize++;
    }

    //Pre-condition: head != null
    protected void insertFirst(DoubleListNode<EntryClass<K, V>> node) {
        head.setPrevious(node);
        node.setNext(head);
        head = node;
        currentSize++;
    }

    @Override
    public V remove(K key) {
        DoubleListNode<EntryClass<K, V>> node = findNode(key);
        if (node == null) return null;

        if (node == head) {
            removeFirstNode();
        } else if (node == tail) {
            removeLastNode();
        } else
            removeMiddleNode(node);

        return node.getElement().getValue();
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
    protected void removeMiddleNode(DoubleListNode<EntryClass<K, V>> node) {
        DoubleListNode<EntryClass<K, V>> prevNode = node.getPrevious();
        DoubleListNode<EntryClass<K, V>> nextNode = node.getNext();
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
        return head.getElement();
    }

    @Override
    public Entry<K, V> maxEntry() throws EmptyDictionaryException {
        if (isEmpty()) throw new EmptyDictionaryException();
        return tail.getElement();
    }

    class EntryIterator implements Iterator<Entry<K, V>>, Serializable {

        static final long serialVersionUID = 0L;

        DoubleListNode<EntryClass<K, V>> next;
        DoubleListNode<EntryClass<K, V>> current;

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
            return current.getElement();
        }

        public void rewind() {
            next = head;
            current = null;
        }
    }
}
